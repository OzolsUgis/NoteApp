package com.ugisozols.noteapp.presentation.notes

import android.content.SharedPreferences
import androidx.lifecycle.*
import com.ugisozols.noteapp.data.local.entities.Notes
import com.ugisozols.noteapp.repository.NoteRepository
import com.ugisozols.noteapp.utitilies.Constants.KEY_EMAIL
import com.ugisozols.noteapp.utitilies.Constants.KEY_PASSWORD
import com.ugisozols.noteapp.utitilies.Constants.NO_EMAIL
import com.ugisozols.noteapp.utitilies.Constants.NO_PASSWORD
import com.ugisozols.noteapp.utitilies.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    repository: NoteRepository,
    private val sharedPreferences: SharedPreferences
): ViewModel() {


    private var userEmail = ""
    private val userEmailFromSP : String? = sharedPreferences.getString(KEY_EMAIL, NO_EMAIL)
    init {
        userEmailFromSP?.let {
            userEmail  = it
        }
    }

    fun setLogoutSharedPreferencesEmailAndPassword(){
        sharedPreferences.edit().putString(KEY_EMAIL, NO_EMAIL).apply()
        sharedPreferences.edit().putString(KEY_PASSWORD, NO_PASSWORD).apply()
    }

    private val _getNotes = repository
        .getAllNotes(userEmail)
        .asLiveData(viewModelScope.coroutineContext)
    val notes : LiveData<Resource<out List<Notes>>> = _getNotes




}
