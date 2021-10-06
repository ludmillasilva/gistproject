package com.example.gistproject.domain

import android.os.Parcelable
import com.example.gistproject.data.response.ResponseGist
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GistParcelable(
    val id: String? = null,
    val login: String? = null,
    val avatar_url: String? = null,
    val type: String? = null,
    val filename: String? = null,
    var isFavorite: Boolean? = null

    ): Parcelable{
    companion object{
        fun parserToGist(responseGist: ResponseGist): GistParcelable?{
            responseGist?.let{
                return GistParcelable(it.owner.login,
                                  it.owner.avatar_url,
                                  it.files.map {file -> file.value.type }.toList().joinToString(", "),
                                  it.files.map {file -> file.value.filename }.toList().joinToString(", "))
            }
            return null;
        }
        fun parserToGistFull(responseGist: ResponseGist): GistParcelable? {
            responseGist.let {
                return GistParcelable(
                    it.id,
                    it.owner.login,
                    it.owner.avatar_url,
                    it.files.map { file -> file.value.type }.toList().joinToString(", "),
                    it.files.map {file -> file.value.filename }.toList().joinToString(", "),
                    it.isFavorite
                )
            }
            return null;
        }
    }
}
