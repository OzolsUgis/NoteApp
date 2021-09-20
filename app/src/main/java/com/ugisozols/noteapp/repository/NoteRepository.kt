package com.ugisozols.noteapp.repository

import android.app.Application
import com.ugisozols.noteapp.data.local.NoteDao
import com.ugisozols.noteapp.data.local.entities.DeletedNotesInDatabase
import com.ugisozols.noteapp.data.local.entities.Notes
import com.ugisozols.noteapp.data.remote.NoteAppApi
import com.ugisozols.noteapp.data.remote.requests.DeleteNoteRequest
import com.ugisozols.noteapp.utitilies.Resource
import com.ugisozols.noteapp.utitilies.checkInternetConnectivity
import com.ugisozols.noteapp.utitilies.networkBoundResources
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber

import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteAppApi: NoteAppApi,
    private val noteDao: NoteDao,
    private val context: Application
) {

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun insertNote(note: Notes) = GlobalScope.launch {
        val insertNote = try {
            noteAppApi.addNotes(note)
        }catch (e: Exception){
            null
        }
        if(insertNote != null && insertNote.isSuccessful){
            noteDao.insertNote(note.apply { isSyncedToServer = true })
        }else{
            noteDao.insertNote(note)
        }
    }

    suspend fun insertAllNotes(notes : List<Notes>){
        notes.forEach{ note->
            insertNote(note)
        }
    }

//    suspend fun deleteNoteById(noteId: String){
//        val deleteNote = try {
//            noteAppApi.deleteNote(DeleteNoteRequest(noteId))
//        }catch (e: Exception){
//            null
//        }
//        noteDao.deleteNoteById(noteId)
//        if(deleteNote == null || !deleteNote.isSuccessful){
//            noteDao.insertDeletedInDatabaseNoteId(DeletedNotesInDatabase(noteId))
//        }else{
//            deleteDeletedInDatabaseNoteIds(noteId)
//        }
//        }
//    suspend fun deleteDeletedInDatabaseNoteIds(noteId : String){
//        noteDao.deleteDeletedInDatabaseNoteIds(noteId)
//    }

    suspend fun getNoteById(noteId : String) = noteDao.getNoteById(noteId)

    fun getAllNotes() : Flow<Resource<out List<Notes>>> {
        return networkBoundResources(
            query = {
                Timber.d("This is from query")
                noteDao.getAllNotes()
            },
            fetch = {

                Timber.d("This is from fetching")
                noteAppApi.getNotes()
            },
            saveFetchResultToDatabase = { response ->

                Timber.d("This is from save fetch result to database ")
                response.body()?.let {
                    insertAllNotes(it)
                }
            },
            shouldFetch = {

                Timber.d("internet checker passed")
                checkInternetConnectivity(context = context)
            }
        )
    }
}