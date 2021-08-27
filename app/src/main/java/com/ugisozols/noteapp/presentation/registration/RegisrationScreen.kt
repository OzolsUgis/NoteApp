package com.ugisozols.noteapp.presentation.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ugisozols.noteapp.presentation.ui.theme.BackgroundColor

@Composable
fun RegistrationScreen(
    registerViewModel: RegisterScreenViewModel = viewModel()
) {
    Box(modifier = Modifier
        .background(color = BackgroundColor)
        .fillMaxSize()
    )

}