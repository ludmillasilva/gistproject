package com.example.gistproject.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gistproject.data.model.GistEntity
import com.example.gistproject.data.repository.GistFavoriteRepository

class GistFavoriteViewModel: ViewModel() {

    var liveGist: LiveData<List<GistEntity>> = MutableLiveData()
    val gistFavoriteRepository: GistFavoriteRepository = GistFavoriteRepository()

    fun getAllGist(){
        gistFavoriteRepository.getAll()
    }
}