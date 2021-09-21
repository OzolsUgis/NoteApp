package com.ugisozols.noteapp.data.remote

import com.ugisozols.noteapp.utitilies.Constants.BASIC_AUTH_IGNORE_URLS
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthInterceptor : Interceptor {

    var email : String? = null
    var password : String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        // Because we don't need authentication for /login and / register we
        // add those end points in Ignore Urls constant and check if encoded
        // path is in those urls if it is then return request proceed
        val request = chain.request()
        if(request.url().encodedPath() in BASIC_AUTH_IGNORE_URLS){
            return chain.proceed(request)
        }

        val authenticatedRequest = request.newBuilder()
                .addHeader("Authorization",
                Credentials.basic(email?:"", password?:"")
            ).build()
        return chain.proceed(authenticatedRequest)
    }
}