package com.ugisozols.noteapp.data.local.entities


import androidx.room.Database
import androidx.room.RoomDatabase
import com.ugisozols.noteapp.data.local.NoteDao


@Database(
    entities = [Notes::class, DeletedNotesInDatabase::class],
    version = 1

)

abstract class NoteDatabase : RoomDatabase(){
    abstract fun noteDao():NoteDao

}