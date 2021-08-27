package com.ugisozols.noteapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.rememberNavController
import com.ugisozols.noteapp.presentation.navigation.Navigation
import com.ugisozols.noteapp.presentation.ui.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

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

