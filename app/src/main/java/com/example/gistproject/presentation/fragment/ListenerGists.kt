package com.example.gistproject.presentation.fragment

import com.example.gistproject.data.response.Owner
import com.example.gistproject.data.response.ResponseGist
import com.example.gistproject.domain.GistParcelable

interface ListenerGists {
    fun getGist (Gist: Int)
    fun getDetailGist(gist: GistParcelable)
    fun handleFavorite(gist:GistParcelable, isFavoriteGist: Boolean)
}