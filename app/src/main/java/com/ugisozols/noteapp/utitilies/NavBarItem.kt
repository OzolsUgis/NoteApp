package com.ugisozols.noteapp.utitilies

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

data class NavBarItem(
    val title : String,
    val route : String,
    val icon : ImageVector
    )