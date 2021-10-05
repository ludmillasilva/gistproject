package com.example.gistproject.data.repository

import androidx.lifecycle.LiveData
import com.example.gistproject.data.dao.GistFavoriteDaoImpl
import com.example.gistproject.data.model.GistEntity

class GistFavoriteRepository {

    private val gistFavoriteDao = GistFavoriteDaoImpl()

    fun getAll(): LiveData<List<GistEntity>> {
        return gistFavoriteDao.getAll();
    }

    suspend fun save(gistEntity: GistEntity) {
        gistFavoriteDao.save(gistEntity)
    }

    suspend fun delete(gistEntity: GistEntity) {
        gistFavoriteDao.remove(gistEntity)
    }

    suspend fun checkIfIsFavorite(id:String): Boolean {
        return gistFavoriteDao.checkIfIsFavorite(id)
    }


}