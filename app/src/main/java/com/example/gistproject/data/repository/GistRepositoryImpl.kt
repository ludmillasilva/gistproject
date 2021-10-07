package com.example.gistproject.data.repository

import com.example.gistproject.data.dao.GistFavoriteDaoImpl
import com.example.gistproject.data.factory.Network
import com.example.gistproject.data.remotesource.RemoteSource
import com.example.gistproject.data.response.ResponseGist
import io.reactivex.Single

class GistRepositoryImpl {
    private val remoteSource: RemoteSource = Network.getRemoteSource()
    private val gistFavoriteDao: GistFavoriteDaoImpl = GistFavoriteDaoImpl()

    fun getGist(currentPage: Int): Single<List<ResponseGist>> {
        return remoteSource.getGists(currentPage).map {
            it.forEach { responseGist ->
                responseGist.isFavorite = gistFavoriteDao.checkIfIsFavorite(responseGist.id)
            }
            it
        }
    }
    fun getSearchGist(searchGist:String): Single<List<ResponseGist>> {
        return remoteSource.getGistsByUsername(searchGist)
            .map { it}
    }

}