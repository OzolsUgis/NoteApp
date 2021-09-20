package com.ugisozols.noteapp.presentation.editnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugisozols.noteapp.data.local.entities.Notes
import com.ugisozols.noteapp.presentation.notes.TopBar
import com.ugisozols.noteapp.repository.NoteRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val repository: NoteRepository
): ViewModel() {

    private val _note = MutableLiveData<Notes>()
    val note : LiveData<Notes> = _note


    fun getNote(noteId : String) = repository.observeNotes(noteId)

    private val _title = MutableLiveData<String>(note.value?.title )
    val title : LiveData<String> = _title

    fun setTitle(newTitle : String){
        _title.value = newTitle
    }

    private val _content = MutableLiveData<String>(note.value?.content )
    val content : LiveData<String> = _content

    fun setContent(newContent : String){
        _content.value = newContent
    }

    fun saveNote(notes: Notes) = viewModelScope.launch{
        repository.insertNote(notes)
    }

}