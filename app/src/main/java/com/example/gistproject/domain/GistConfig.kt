package com.example.gistproject.domain

import android.os.Parcelable
import com.example.gistproject.data.response.Owner
import com.example.gistproject.data.response.ResponseGist
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GistConfig(
    val login: String? = null,
    val avatar_url: String? = null,

    ): Parcelable{
    companion object{
        fun parserToGist(owner: Owner): GistConfig?{
            owner?.let{
                return GistConfig(it.login, it.avatar_url)
            }
            return null;
        }
    }
}
