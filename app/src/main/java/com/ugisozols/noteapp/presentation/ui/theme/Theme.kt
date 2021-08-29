package com.ugisozols.noteapp.presentation.ui.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors

import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = MainAccent,
    background = BackgroundColor,
    onPrimary = ButtonTextColor,
    surface = SurfaceColor,
    onSurface = TextColor

)


@Composable
fun NoteAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}