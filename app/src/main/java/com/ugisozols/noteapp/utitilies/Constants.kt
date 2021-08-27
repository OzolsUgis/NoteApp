package com.ugisozols.noteapp.utitilies

object Constants {

    val BASIC_AUTH_IGNORE_URLS = listOf("/login","/register")

    const val DATABASE_NAME = "notes_db"
    const val BASE_URL = "http://10.0.2.2:8000"

    // Errors

    const val SERVER_CONNECTION_ERROR = "Couldn't connect to the server"

    // Routes

    const val REGISTER_SCREEN_ROUTE = "register"
}