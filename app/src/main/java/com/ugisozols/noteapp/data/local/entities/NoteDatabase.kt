package com.ugisozols.noteapp.data.local.entities


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ugisozols.noteapp.data.local.Converters
import com.ugisozols.noteapp.data.local.FolderDao
import com.ugisozols.noteapp.data.local.NoteDao


@Database(
    entities = [Notes::class, Folders::class],
    version = 1

)
@TypeConverters(Converters::class)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun noteDao():NoteDao
    abstract fun folderDao():FolderDao
}