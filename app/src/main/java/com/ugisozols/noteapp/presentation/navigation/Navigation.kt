package com.ugisozols.noteapp.presentation.navigation

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ugisozols.noteapp.utitilies.Screen

@Composable
fun Navigation( navController : NavHostController) {
    NavHost(navController = navController, startDestination = ""){
        composable(Screen.Register.route){

        }

    }
}