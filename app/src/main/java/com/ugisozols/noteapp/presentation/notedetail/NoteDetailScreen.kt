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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ugisozols.noteapp.R
import com.ugisozols.noteapp.presentation.components.ShowAlertDialog
import com.ugisozols.noteapp.presentation.ui.theme.MainAccent
import com.ugisozols.noteapp.presentation.ui.theme.paddingLarge
import com.ugisozols.noteapp.presentation.ui.theme.paddingMedium
import com.ugisozols.noteapp.presentation.ui.theme.paddingSmall
import com.ugisozols.noteapp.utitilies.Screen
import com.ugisozols.noteapp.utitilies.getDateFromTimestamp



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
        val (dialogIsOpen, setShowDialog) = remember { mutableStateOf(false)}
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = MainAccent,
                modifier = Modifier.clickable {
                    navController.navigate(Screen.Notes.route)
                }
            )
            Spacer(modifier = Modifier.width(paddingSmall))
            Text(text = stringResource(id = R.string.back_button), modifier = Modifier.clickable {
                navController.navigate(Screen.Notes.route)
            })
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                tint = MainAccent,
                modifier = Modifier.clickable {
                    navController.navigate(Screen.EditNote.withArgs(noteId))
                }
            )
            Spacer(modifier = Modifier.width(paddingLarge))
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = MainAccent,
                modifier = Modifier.clickable {
                    setShowDialog(true)
                }
            )
            if (dialogIsOpen){
                ShowAlertDialog(
                    title = stringResource(id = R.string.alert_dialog_delete_title),
                    content = stringResource(id = R.string.alert_dialog_delete_content),
                    confirm = stringResource(id = R.string.confirm),
                    decline = stringResource(id = R.string.decline) ,
                    onDismiss = { setShowDialog(false)},
                    onConfirmClick = {
                        viewModel.deleteNote(noteId)
                        setShowDialog(false)
                        navController.navigate(Screen.Notes.route){
                            popUpTo(Screen.Notes.route){
                                inclusive = true
                            }
                        }
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