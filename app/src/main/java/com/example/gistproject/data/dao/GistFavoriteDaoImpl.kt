package com.example.gistproject.data.dao

import androidx.lifecycle.LiveData
import com.example.gistproject.ApplicationContext
import com.example.gistproject.data.database.GistDatabase
import com.example.gistproject.data.model.GistEntity

class GistFavoriteDaoImpl: GistFavoriteDao {
    override suspend fun save(favorite: GistEntity) {
        val db: GistDatabase = GistDatabase.getInstance(ApplicationContext.appContext)
        db.gistFavoriteDao(). save(favorite)
    }

    override suspend fun remove(favorite: GistEntity) {
        val db: GistDatabase = GistDatabase.getInstance(ApplicationContext.appContext)
        db.gistFavoriteDao().remove(favorite)
    }

    override fun getAll(): LiveData<List<GistEntity>> {
        val db: GistDatabase = GistDatabase.getInstance(ApplicationContext.appContext)
        return db.gistFavoriteDao().getAll()
    }

    override fun checkIfIsFavorite(id: String): Boolean {
        val db: GistDatabase = GistDatabase.getInstance(ApplicationContext.appContext)
        return db.gistFavoriteDao().checkIfIsFavorite(id)
    }


}
