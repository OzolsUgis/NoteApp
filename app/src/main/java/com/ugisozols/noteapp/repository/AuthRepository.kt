package com.ugisozols.noteapp.repository

import com.ugisozols.noteapp.data.remote.NoteAppApi
import com.ugisozols.noteapp.data.remote.requests.AccountRequest
import com.ugisozols.noteapp.utitilies.Constants.SERVER_CONNECTION_ERROR
import com.ugisozols.noteapp.utitilies.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject


class AuthRepository @Inject constructor(
    private val noteAppApi: NoteAppApi
) {
    suspend fun register(
        email: String,
        password: String
    ): Resource<String> = withContext(Dispatchers.IO){
        try {
            val apiCallResponse = noteAppApi.register(AccountRequest(email, password))
            if(apiCallResponse.isSuccessful){
                Resource.Success(apiCallResponse.body()?.message)
            }else{
                Resource.Error(apiCallResponse.message(), null)
            }
        }catch (e : Exception){
            Resource.Error(SERVER_CONNECTION_ERROR, null)
        }
    }
}