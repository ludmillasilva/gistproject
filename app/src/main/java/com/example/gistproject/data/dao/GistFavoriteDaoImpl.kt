package com.example.gistproject.data.dao

import androidx.lifecycle.LiveData
import com.example.gistproject.ApplicationContext
import com.example.gistproject.data.database.GistDatabase
import com.example.gistproject.data.model.GistEntity

class GistFavoriteDaoImpl: GistFavoriteDao {
    override suspend fun save(favorite: GistEntity) {
        db.gistFavoriteDao(). save(favorite)
    }

    override suspend fun remove(favorite: GistEntity) {
        db.gistFavoriteDao().remove(favorite)
    }

    override fun getAll(): LiveData<List<GistEntity>> {
        return db.gistFavoriteDao().getAll()
    }

    override fun checkIfIsFavorite(id: String): Boolean {
        return db.gistFavoriteDao().checkIfIsFavorite(id)
    }

    companion object{
        var db: GistDatabase = GistDatabase.getInstance(ApplicationContext.appContext)
    }

}