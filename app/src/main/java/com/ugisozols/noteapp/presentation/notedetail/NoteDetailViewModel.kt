package com.ugisozols.noteapp.presentation.notedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugisozols.noteapp.data.local.entities.Notes
import com.ugisozols.noteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val repository: NoteRepository
    ):ViewModel() {

    fun deleteNote(noteId: String) = viewModelScope.launch {
        repository.deleteNoteById(noteId)
    }

    private val _note = MutableLiveData<Notes>()
    val note : LiveData<Notes> = _note
        fun getNoteById(noteId : String){
            viewModelScope.launch {
                _note.value = repository.getNoteById(noteId)
            }
        }
}