package com.ugisozols.noteapp.presentation.newnotes

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.security.crypto.EncryptedSharedPreferences
import com.ugisozols.noteapp.data.local.entities.Notes
import com.ugisozols.noteapp.repository.NoteRepository
import com.ugisozols.noteapp.utitilies.Constants.KEY_EMAIL
import com.ugisozols.noteapp.utitilies.Constants.NO_EMAIL
import com.ugisozols.noteapp.utitilies.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewNotesViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val sharedPreferences: SharedPreferences
): ViewModel() {

    private val _titleInput = MutableLiveData<String>("")
    val titleInput : LiveData<String> = _titleInput

    fun onTitleChange(newTitle : String){
        _titleInput.value = newTitle
    }

    private val _contentInput = MutableLiveData<String>("")
    val contentInput : LiveData<String> = _contentInput

    fun onContentChange(content : String){
        _contentInput.value = content
    }


    fun saveNote(notes: Notes) = GlobalScope.launch{
            noteRepository.insertNote(notes)
    }

    fun getEmailFromSharedPref() : String{
        return sharedPreferences.getString(KEY_EMAIL, NO_EMAIL) ?: NO_EMAIL
    }
}