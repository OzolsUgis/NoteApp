package com.ugisozols.noteapp.utitilies

import com.ugisozols.noteapp.utitilies.Constants.REGISTER_SCREEN_ROUTE

sealed class Screen (val route : String){
    object Register : Screen(REGISTER_SCREEN_ROUTE)
}
