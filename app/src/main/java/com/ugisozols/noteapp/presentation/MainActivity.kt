package com.ugisozols.noteapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.ugisozols.noteapp.presentation.navigation.Navigation
import com.ugisozols.noteapp.presentation.registration.RegisterScreenViewModel
import com.ugisozols.noteapp.presentation.ui.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                val navController = rememberNavController()
                Surface(color = MaterialTheme.colors.background) {
                    Navigation(navController = navController)
                }
            }
        }
    }
}

