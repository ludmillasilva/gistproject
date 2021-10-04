package com.example.gistproject.presentation.fragment

import com.example.gistproject.data.response.Owner

interface ListenerGists {
    fun getGist (Gist: Int)
    fun getDetailGist(GistId: Owner)
}