package com.ugisozols.noteapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deleted_note_ids")
data class DeletedNotesInDatabase(
    @PrimaryKey(autoGenerate = false)
    val deletedNoteId: String
)
