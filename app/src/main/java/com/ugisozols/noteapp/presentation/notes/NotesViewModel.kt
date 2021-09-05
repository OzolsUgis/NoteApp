package com.ugisozols.noteapp.presentation.notes

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.*
import com.ugisozols.noteapp.data.local.entities.Notes
import com.ugisozols.noteapp.repository.AuthRepository
import com.ugisozols.noteapp.repository.NoteRepository
import com.ugisozols.noteapp.utitilies.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val repository: NoteRepository
): ViewModel() {

    private val _getNotes = repository
        .getAllNotes()
        .asLiveData(viewModelScope.coroutineContext)
    val notes : LiveData<Resource<out List<Notes>>> = _getNotes




}
