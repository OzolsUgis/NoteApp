package com.ugisozols.noteapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import java.util.*

@Entity(tableName = "folders")
data class Folders(
    val folderName : String,
    val notes : List<Notes>? = null,
    val userEmail : String,
    @Expose(deserialize = false, serialize = false)
    val isSyncedToServer : Boolean,
    @PrimaryKey
    val id : String = UUID.randomUUID().toString()
)
