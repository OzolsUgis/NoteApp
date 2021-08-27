package com.ugisozols.noteapp.di

import android.content.Context
import androidx.room.Room
import com.ugisozols.noteapp.data.local.FolderDao
import com.ugisozols.noteapp.data.local.entities.NoteDatabase
import com.ugisozols.noteapp.data.remote.BasicAuthInterceptor
import com.ugisozols.noteapp.data.remote.NoteAppApi
import com.ugisozols.noteapp.utitilies.Constants.BASE_URL
import com.ugisozols.noteapp.utitilies.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNoteDatabase(
        @ApplicationContext context: Context
    )= Room.databaseBuilder(
        context,
        NoteDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideFolderDao(database : NoteDatabase) = database.folderDao()


    @Singleton
    @Provides
    fun provideNoteDao(database : NoteDatabase) = database.noteDao()

    @Singleton
    @Provides
    fun provideBasicAuthInterceptor() = BasicAuthInterceptor()

    @Singleton
    @Provides
    fun provideNoteApi(
        basicAuthInterceptor: BasicAuthInterceptor
    ) : NoteAppApi {
        val client = OkHttpClient.Builder()
            .addInterceptor(basicAuthInterceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NoteAppApi::class.java)
    }
}





















