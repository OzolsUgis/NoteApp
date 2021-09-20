package com.ugisozols.noteapp.presentation.notedetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.material.timepicker.TimeFormat
import com.ugisozols.noteapp.data.local.entities.Notes
import com.ugisozols.noteapp.presentation.components.ShowAlertDialog
import com.ugisozols.noteapp.presentation.ui.theme.MainAccent
import com.ugisozols.noteapp.presentation.ui.theme.paddingLarge
import com.ugisozols.noteapp.presentation.ui.theme.paddingMedium
import com.ugisozols.noteapp.presentation.ui.theme.paddingSmall
import com.ugisozols.noteapp.utitilies.Screen
import com.ugisozols.noteapp.utitilies.getDateFromTimestamp
import timber.log.Timber


@Composable
fun NoteDetailScreen(
    noteId: String,
    navController: NavController,
    noteDetailViewModel: NoteDetailViewModel = hiltViewModel()
) {
    Column(Modifier.padding(paddingMedium).fillMaxSize()) {
        noteDetailViewModel.getNoteById(noteId)
        val note = noteDetailViewModel.note.value
        TopBarSection(noteId = noteId,navController = navController, noteDetailViewModel)
        Spacer(Modifier.height(paddingMedium))
        Spacer(Modifier.height(paddingMedium))
        NoteDetailHeading(note?.title.orEmpty())
        Spacer(Modifier.height(paddingMedium))
        Date(date = note?.date)
        Spacer(Modifier.height(paddingMedium))
        NoteContent(note?.content.orEmpty())
    }

}

@Composable
fun TopBarSection(noteId: String,navController : NavController, viewModel: NoteDetailViewModel) {
    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        var (dialogIsOpen, setShowDialog) = remember { mutableStateOf(false)}
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "back",
                tint = MainAccent,
                modifier = Modifier.clickable {
                    navController.navigate(Screen.Notes.route)
                }
            )
            Spacer(modifier = Modifier.width(paddingSmall))
            Text(text = "Back", modifier = Modifier.clickable {
                navController.navigate(Screen.Notes.route)
            })
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit",
                tint = MainAccent,
                modifier = Modifier.clickable {
                    Timber.d(noteId)
                    navController.navigate(Screen.EditNote.withArgs(noteId))
                }
            )
            Spacer(modifier = Modifier.width(paddingLarge))
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = MainAccent,
                modifier = Modifier.clickable {
                    setShowDialog(true)
                }
            )
            if (dialogIsOpen){
                ShowAlertDialog(
                    title = "Delete note",
                    content = "Are you ure you want to delete this note?",
                    confirm = "Yes",
                    decline = "No" ,
                    onDismiss = { setShowDialog(false)},
                    onConfirmClick = {
                        viewModel.deleteNote(noteId)
                        setShowDialog(false)
                        navController.navigate(Screen.Notes.route)
                    },
                    onDeclineClick = {
                        setShowDialog(false)
                    },

                )
            }
        }
    }
}

@Composable
fun NoteDetailHeading(title : String) {
    Text(text = title, style = MaterialTheme.typography.h1)
}

@Composable
fun Date(date : Long?) {
        date?.let {
        Text(text = getDateFromTimestamp(it), style = MaterialTheme.typography.body2)
    }

}

@Composable
fun NoteContent(content : String) {
    Text(text = content)
}