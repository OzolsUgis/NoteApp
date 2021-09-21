package com.ugisozols.noteapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.ugisozols.noteapp.R

import com.ugisozols.noteapp.presentation.editnote.EditNoteScreen
import com.ugisozols.noteapp.presentation.login.LoginScreen
import com.ugisozols.noteapp.presentation.newnotes.NewNotesScreen
import com.ugisozols.noteapp.presentation.notedetail.NoteDetailScreen
import com.ugisozols.noteapp.presentation.notes.NoteScreen
import com.ugisozols.noteapp.presentation.registration.RegistrationScreen
import com.ugisozols.noteapp.utitilies.Screen

@Composable
fun NavHostController.Navigation() {
    NavHost(navController = this, startDestination = Screen.Login.route){
        composable(Screen.Register.route){
            RegistrationScreen(this@Navigation)
        }
        composable(Screen.Login.route) {
            LoginScreen(this@Navigation)
        }
        composable(Screen.Notes.route){
            NoteScreen(this@Navigation)
        }
        composable(Screen.NewNotes.route){
            NewNotesScreen(navController = this@Navigation)
        }
        composable(
            route = Screen.NoteDetail.route + "/{noteId}",
            arguments = listOf(
                navArgument("noteId"){
                    type = NavType.StringType
            }
            )
        ){ noteDetailScreenArgs ->
            NoteDetailScreen(noteId = noteDetailScreenArgs.arguments?.getString(stringResource(id = R.string.noteId_arg)).orEmpty(), this@Navigation)
        }
        composable(
            route = Screen.EditNote.route + "/{noteId}",
            arguments = listOf(
                navArgument("noteId"){
                    type = NavType.StringType
                }
            )
        ){ editNoteArgs ->
            EditNoteScreen(noteId = editNoteArgs.arguments?.getString(stringResource(id = R.string.noteId_arg)).orEmpty(), navController = this@Navigation)
        }

    }
}