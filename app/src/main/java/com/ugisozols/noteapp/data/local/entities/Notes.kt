package com.ugisozols.noteapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import java.util.*


@Entity(tableName = "notes")
data class Notes(
    val title : String,
    val date : Long,
    val content : String,
    val userEmail : String,
    @Expose(deserialize = false, serialize = false)
    var isSyncedToServer : Boolean,
    @PrimaryKey
    val id : String = UUID.randomUUID().toString()
)
