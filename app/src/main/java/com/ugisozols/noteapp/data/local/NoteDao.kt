package com.ugisozols.noteapp.data.local


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ugisozols.noteapp.data.local.entities.DeletedNotesInDatabase
import com.ugisozols.noteapp.data.local.entities.Notes
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note : Notes)

    @Query("DELETE FROM notes WHERE id= :noteId")
    suspend fun deleteNoteById(noteId : String)

    // isSyncedToServer = true
    @Query("DELETE FROM notes WHERE isSyncedToServer = 1 ")
    suspend fun deleteAllSyncedNotes()

    // isSyncedToServer = false
    @Query("SELECT * FROM notes WHERE isSyncedToServer = 0")
    suspend fun getAllUnsyncedNotes() : List<Notes>


    @Query("SELECT * FROM notes WHERE id= :noteId")
    fun observeNoteById(noteId: String): LiveData<Notes>

    @Query("SELECT * FROM notes WHERE id= :noteId")
    suspend fun getNoteById(noteId: String) : Notes?

    @Query("SELECT * FROM notes ORDER BY date DESC")
    fun getAllNotes(): Flow<List<Notes>>

//    @Query("SELECT * FROM deleted_note_ids")
//    suspend fun getAllDeletedInDatabaseNoteIds() : List<DeletedNotesInDatabase>
//
//    @Query("DELETE FROM deleted_note_ids WHERE deletedNoteId = :deletedNoteId")
//    suspend fun deleteDeletedInDatabaseNoteIds(deletedNoteId : String)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertDeletedInDatabaseNoteId(deletedNoteId: DeletedNotesInDatabase)
}