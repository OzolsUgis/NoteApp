package com.ugisozols.noteapp.utitilies

import java.text.SimpleDateFormat
import java.util.*

fun getDateFromTimestamp(time : Long) : String {
    val dateFormat = SimpleDateFormat("dd-MMMM-yyyy HH:mm")
    val timestamp = Date(time)
    return dateFormat.format(timestamp)
}