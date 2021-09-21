package com.ugisozols.noteapp.utitilies

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun getDateFromTimestamp(time : Long) : String {
    val dateFormat = SimpleDateFormat("dd-MMMM-yyyy HH:mm")
    val timestamp = Date(time)
    return dateFormat.format(timestamp)
}