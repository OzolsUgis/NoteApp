package com.ugisozols.noteapp.data.local


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ugisozols.noteapp.data.local.entities.Folders
import com.ugisozols.noteapp.data.local.entities.Notes
import kotlinx.coroutines.flow.Flow

@Dao
interface FolderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFolder(folder : Folders)

    @Query("DELETE FROM folders WHERE id= :folderId")
    suspend fun deleteFolderById(folderId: String)

    // isSyncedToServer = true
    @Query("DELETE FROM folders WHERE isSyncedToServer = 1 ")
    suspend fun deleteAllSyncedFolders()

    // isSyncedToServer = false
    @Query("SELECT * FROM folders WHERE isSyncedToServer = 0")
    suspend fun getAllUnsyncedFolders() : List<Folders>


    @Query("SELECT * FROM folders WHERE id= :folderId")
    fun observeFolderById(folderId: String): LiveData<Folders>

    @Query("SELECT * FROM folders WHERE id= :folderId")
    suspend fun getFolderById(folderId: String) : Folders?

    @Query("SELECT * FROM folders ")
    fun getAllFolders(): Flow<List<Folders>>


}