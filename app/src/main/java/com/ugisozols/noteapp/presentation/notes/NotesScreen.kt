package com.ugisozols.noteapp.presentation.notes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ugisozols.noteapp.data.local.entities.Notes
import com.ugisozols.noteapp.presentation.ui.theme.MainAccent
import com.ugisozols.noteapp.presentation.ui.theme.loadingProgressBarWidth
import com.ugisozols.noteapp.utitilies.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import timber.log.Timber

@Composable
fun NoteScreen(
    viewModel: NotesViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Notes(viewModel = viewModel)
    }
}

@Composable
fun Notes(
    viewModel: NotesViewModel
) {
    val notesDataState = viewModel.notes.observeAsState()
    val listIsEmpty = notesDataState.value?.data?.isEmpty() ?: return Unit
    when(notesDataState.value){
        is Resource.Loading -> {
            CircularProgressIndicator(
                color = MainAccent,
                strokeWidth = loadingProgressBarWidth,
                modifier = Modifier
                    .size(100.dp)
                    .wrapContentSize()
            )
        }
        is Resource.Success -> {
            if(listIsEmpty){
                EmptyState()
            }else{
                NotEmptyState()
            }
        }
        is Resource.Error -> {
            ErrorState()
        }
    }

}

@Composable
fun EmptyState() {
    Text(text = "This is when empty list")
}


@Composable
fun NotEmptyState() {
    Text(text = "This is when there are some list items")
}


@Composable
fun ErrorState() {
    Text(text = "This is when Error state ")
}