package com.ugisozols.noteapp.utitilies

import com.ugisozols.noteapp.utitilies.Constants.LOGIN_SCREEN_ROUTE
import com.ugisozols.noteapp.utitilies.Constants.REGISTER_SCREEN_ROUTE

sealed class Screen (val route : String){
    object Register : Screen(REGISTER_SCREEN_ROUTE)
    object Login : Screen(LOGIN_SCREEN_ROUTE)
}
