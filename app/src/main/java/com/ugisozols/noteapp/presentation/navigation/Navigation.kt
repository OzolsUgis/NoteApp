package com.ugisozols.noteapp.presentation.navigation

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument

import com.ugisozols.noteapp.presentation.editnote.EditNoteScreen
import com.ugisozols.noteapp.presentation.login.LoginScreen
import com.ugisozols.noteapp.presentation.newnotes.NewNotesScreen
import com.ugisozols.noteapp.presentation.notedetail.NoteDetailScreen
import com.ugisozols.noteapp.presentation.notes.NoteScreen
import com.ugisozols.noteapp.presentation.registration.RegistrationScreen
import com.ugisozols.noteapp.utitilies.Screen

@Composable
fun Navigation( navController : NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Login.route){
        composable(Screen.Register.route){
            RegistrationScreen(navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.Notes.route){
            NoteScreen(navController)
        }
        composable(Screen.NewNotes.route){
            NewNotesScreen(navController = navController)
        }
        composable(
            route = Screen.NoteDetail.route + "/{noteId}",
            arguments = listOf(
                navArgument("noteId"){
                    type = NavType.StringType
            }
            )
        ){ noteDetailScreenArgs ->
            NoteDetailScreen(noteId = noteDetailScreenArgs.arguments?.getString("noteId").orEmpty(), navController)
        }
        composable(
            route = Screen.EditNote.route + "/{noteId}",
            arguments = listOf(
                navArgument("noteId"){
                    type = NavType.StringType
                }
            )
        ){ editNoteArgs ->
            EditNoteScreen(noteId = editNoteArgs.arguments?.getString("noteId").orEmpty(), navController = navController)
        }

    }
}