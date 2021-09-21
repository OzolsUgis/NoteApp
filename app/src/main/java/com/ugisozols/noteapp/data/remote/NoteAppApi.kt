package com.ugisozols.noteapp.data.remote

import com.ugisozols.noteapp.data.local.entities.Notes
import com.ugisozols.noteapp.data.remote.requests.AccountRequest
import com.ugisozols.noteapp.data.remote.requests.DeleteNoteRequest
import com.ugisozols.noteapp.data.remote.responses.MainResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NoteAppApi {

    @POST("/register")
    suspend fun register(
        @Body registerRequest : AccountRequest
    ): Response<MainResponse>

    @POST("/login")
    suspend fun login(
        @Body loginRequest: AccountRequest
    ):Response<MainResponse>

    @POST("/addNotes")
    suspend fun addNotes(
        @Body notes: Notes
    ):Response<MainResponse>

    @POST("/deleteNote")
    suspend fun deleteNote(
        @Body deleteNoteRequest: DeleteNoteRequest
    ): Response<MainResponse>

    @GET("/getNotes")
    suspend fun getNotes(): Response<List<Notes>>







}