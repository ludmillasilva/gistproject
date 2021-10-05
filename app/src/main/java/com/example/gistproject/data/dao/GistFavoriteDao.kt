package com.example.gistproject.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gistproject.data.model.GistEntity

@Dao
interface GistFavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(favorite : GistEntity)

    @Delete
    suspend fun remove (favorite : GistEntity)

    @Query("SELECT * from gist")
    fun getAll(): LiveData<List<GistEntity>>

    @Query("SELECT EXISTS (SELECT * FROM gist WHERE id =:id)")
    fun checkIfIsFavorite(id: String) :Boolean
}