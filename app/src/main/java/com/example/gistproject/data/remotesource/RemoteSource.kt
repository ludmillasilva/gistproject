package com.example.gistproject.data.remotesource

import com.example.gistproject.data.response.ResponseGist
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteSource {

    @GET("/gists/public")
    fun getGists(@Query("page") currentPage: Int): Single<List<ResponseGist>>

    @GET("/users/{username}/gists")
    fun getGistsByUsername(@Path("username") username:String): Single<List<ResponseGist>>

}