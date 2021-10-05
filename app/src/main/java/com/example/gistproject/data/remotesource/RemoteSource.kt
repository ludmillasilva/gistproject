package com.example.gistproject.data.remotesource

import com.example.gistproject.data.response.ResponseGist
import io.reactivex.Single
import retrofit2.http.GET

interface RemoteSource {

    @GET("/gists/public")
    fun getGists(): Single<List<ResponseGist>>



}