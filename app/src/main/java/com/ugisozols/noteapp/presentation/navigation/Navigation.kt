package com.ugisozols.noteapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ugisozols.noteapp.data.remote.BasicAuthInterceptor
import com.ugisozols.noteapp.presentation.login.LoginScreen
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

    }
}