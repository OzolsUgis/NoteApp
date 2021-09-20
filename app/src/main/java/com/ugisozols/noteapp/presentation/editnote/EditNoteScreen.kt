package com.ugisozols.noteapp.presentation.editnote

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ugisozols.noteapp.data.local.entities.Notes
import com.ugisozols.noteapp.presentation.components.StandardTextField
import com.ugisozols.noteapp.presentation.newnotes.NewNoteHeading
import com.ugisozols.noteapp.presentation.newnotes.NewNotesViewModel
import com.ugisozols.noteapp.presentation.ui.theme.*
import com.ugisozols.noteapp.utitilies.Screen
import timber.log.Timber
import java.util.*


@Composable
fun EditNoteScreen(viewModel: EditNoteViewModel = hiltViewModel(),noteId : String, navController: NavController) {
    val note by viewModel.getNote(noteId).observeAsState()
    Timber.d(note.toString())
    note?.let {
        viewModel.setTitle(it.title)
        viewModel.setContent(it.content)
    }
    Column(modifier = Modifier.padding(paddingMedium)) {
        TopBarSection(noteId = noteId, email = note?.userEmail.orEmpty(), viewModel = viewModel, navController = navController )
        Spacer(Modifier.height(paddingMedium))
        EditNoteHeading()
        Spacer(Modifier.height(paddingMedium))
        TitleInput(viewModel = viewModel)
        Spacer(Modifier.height(paddingMedium))
        ContentInput(viewModel = viewModel)

    }
}

@Composable
fun TopBarSection(
    noteId: String,
    email: String,
    viewModel: EditNoteViewModel,
    navController: NavController
) {

    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        Row (verticalAlignment = Alignment.CenterVertically){
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back",tint = MainAccent)
            Spacer(modifier = Modifier.width(paddingSmall))
            Text(text ="Back", modifier = Modifier.clickable {
                navController.navigate(Screen.Notes.route)
            })
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.Done, contentDescription = "Done", tint = MainAccent)
            Spacer(modifier = Modifier.width(paddingSmall))
            Text(text = "Save", Modifier.clickable {
                val title = viewModel.title.value.orEmpty()
                val date = System.currentTimeMillis()
                val id = noteId
                val content = viewModel.content.value.orEmpty()
                val email = email
                viewModel.saveNote(
                    Notes(
                    title = title,
                    content = content,
                    date = date,
                    isSyncedToServer = false,
                    id = id,
                    userEmail = email
                )
                )
                Timber.d(email)
                navController.navigate(Screen.Notes.route)

            })

        }
    }
}

@Composable
fun EditNoteHeading() {
    Text(text = "Edit Note",style = MaterialTheme.typography.h1)
}

@Composable
fun TitleInput(viewModel: EditNoteViewModel) {
    val title by viewModel.title.observeAsState()
    Timber.d("title = $title")
    title?.let {
        StandardTextField(
            onValueChange = {
                viewModel.setTitle(it)
            },
            hint = "Enter title here",
            text =  it
        )
    }
}

@Composable
fun ContentInput(viewModel: EditNoteViewModel){
    val content by viewModel.content.observeAsState()
    content?.let {
        TextField(
            value = it,
            onValueChange = {
                viewModel.setContent(it)
            },
            shape = RoundedCornerShape(textfieldRaundedCorners),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = SurfaceColor,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            )
        )
    }

}

