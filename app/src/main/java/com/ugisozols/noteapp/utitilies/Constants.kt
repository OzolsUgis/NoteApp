package com.ugisozols.noteapp.utitilies

object Constants {

    val BASIC_AUTH_IGNORE_URLS = listOf("/login","/register")

    const val DATABASE_NAME = "notes_db"
    const val BASE_URL = "http://10.0.2.2:8001"
    const val MIN_PASSWORD_LENGTH = 8

    // Shared Preferences
    const val ENCRYPTED_SHARED_PREFERENCES_NAME = "enc_sh_pref"
    const val KEY_EMAIL = "KEY_EMAIL"
    const val KEY_PASSWORD = "KEY_PASSWORD"
    const val NO_EMAIL = "NO_EMAIL"
    const val NO_PASSWORD = "NO_PASSWORD"


    // Errors

    const val SERVER_CONNECTION_ERROR = "Couldn't connect to the server!"
    const val EMPTY_FIELD_ERROR = "Please fill in all fields!"
    const val TOO_SHORT_PASSWORD_ERROR = "Password is too short !"
    const val PASSWORDS_DO_NOT_MATCH_ERROR = "Passwords do not match!"

    // Routes

    const val REGISTER_SCREEN_ROUTE = "register"
    const val LOGIN_SCREEN_ROUTE = "login"
    const val NOTES_SCREEN_ROUTE = "notes"
    const val NEW_NOTES_SCREEN_ROUTE = "new_note"


}