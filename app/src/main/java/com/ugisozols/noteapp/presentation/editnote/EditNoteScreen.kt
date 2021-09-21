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
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ugisozols.noteapp.R
import com.ugisozols.noteapp.data.local.entities.Notes
import com.ugisozols.noteapp.presentation.components.StandardTextField
import com.ugisozols.noteapp.presentation.ui.theme.*
import com.ugisozols.noteapp.utitilies.Screen

@Composable
fun EditNoteScreen(viewModel: EditNoteViewModel = hiltViewModel(),noteId : String, navController: NavController) {
    val note by viewModel.getNote(noteId).observeAsState()
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
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null,tint = MainAccent)
            Spacer(modifier = Modifier.width(paddingSmall))
            Text(text = stringResource(id = R.string.back_button), modifier = Modifier.clickable {
                navController.navigate(Screen.Notes.route)
            })
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.Done, contentDescription = null, tint = MainAccent)
            Spacer(modifier = Modifier.width(paddingSmall))
            Text(text = stringResource(id = R.string.save_button), Modifier.clickable {
                val title = viewModel.title.value.orEmpty()
                val date = System.currentTimeMillis()
                val content = viewModel.content.value.orEmpty()
                viewModel.saveNote(
                    Notes(
                    title = title,
                    content = content,
                    date = date,
                    isSyncedToServer = false,
                    id = noteId,
                    userEmail = email
                )
                )
                navController.navigate(Screen.Notes.route)

            })

        }
    }
}

@Composable
fun EditNoteHeading() {
    Text(text = stringResource(id = R.string.edit_note_title),style = MaterialTheme.typography.h1)
}

@Composable
fun TitleInput(viewModel: EditNoteViewModel) {
    val title by viewModel.title.observeAsState()
    title?.let {
        StandardTextField(
            onValueChange = { title ->
                viewModel.setTitle(title)
            },
            hint = stringResource(id = R.string.title_input_hint),
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
            onValueChange = { content->
                viewModel.setContent(content)
            },
            shape = RoundedCornerShape(textfieldRoundedCorners),
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

