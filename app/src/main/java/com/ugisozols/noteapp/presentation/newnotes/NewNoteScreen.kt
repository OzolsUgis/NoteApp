package com.ugisozols.noteapp.presentation.newnotes

import android.widget.Space
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.security.crypto.EncryptedSharedPreferences
import com.ugisozols.noteapp.data.local.entities.Notes
import com.ugisozols.noteapp.presentation.components.StandardTextField
import com.ugisozols.noteapp.presentation.ui.theme.*
import com.ugisozols.noteapp.utitilies.Constants.KEY_EMAIL
import com.ugisozols.noteapp.utitilies.Constants.NO_EMAIL
import com.ugisozols.noteapp.utitilies.Screen
import timber.log.Timber
import java.util.*

@Composable
fun NewNotesScreen( navController : NavController,viewModel: NewNotesViewModel = hiltViewModel()) {
    Column(modifier = Modifier.padding(paddingMedium)) {
        TopBarSection(viewModel,navController)
        Spacer(Modifier.height(paddingMedium))
        NewNoteHeading()
        Spacer(Modifier.height(paddingMedium))
        TitleInput(viewModel = viewModel)
        Spacer(Modifier.height(paddingMedium))
        ContentInput(viewModel = viewModel)

    }
}

@Composable
fun TopBarSection(
    viewModel: NewNotesViewModel,
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
                val title = viewModel.titleInput.value.orEmpty()
                val date = System.currentTimeMillis()
                val id = UUID.randomUUID().toString()
                val content = viewModel.contentInput.value.orEmpty()
                val email = viewModel.getEmailFromSharedPref()
                viewModel.saveNote(Notes(
                    title = title,
                    content = content,
                    date = date,
                    isSyncedToServer = false,
                    id = id,
                    userEmail = email
                ))
                Timber.d(email)
                navController.navigate(Screen.Notes.route)

            })

        }
    }
}

@Composable
fun NewNoteHeading() {
    Text(text = "New note",style = MaterialTheme.typography.h1)
}

@Composable
fun TitleInput(viewModel: NewNotesViewModel) {
    val title by viewModel.titleInput.observeAsState(initial = "")
    StandardTextField(
        onValueChange = {
            viewModel.onTitleChange(it)
        },
        hint = "Enter title here",
        text = title
    )
}

@Composable
fun ContentInput(viewModel: NewNotesViewModel){
    val content by viewModel.contentInput.observeAsState(initial = "")
    TextField(
        value = content,
        onValueChange = {
            viewModel.onContentChange(it)
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

