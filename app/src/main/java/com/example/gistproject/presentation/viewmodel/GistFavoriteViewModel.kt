package com.example.gistproject.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gistproject.data.model.GistEntity
import com.example.gistproject.data.repository.GistFavoriteRepository
import kotlinx.coroutines.launch

class GistFavoriteViewModel: ViewModel() {

    var liveGist: LiveData<List<GistEntity>> = MutableLiveData()
    val gistFavoriteRepository: GistFavoriteRepository = GistFavoriteRepository()

    fun getAllGist(){
        liveGist = gistFavoriteRepository.getAll()
    }

    fun deleteGist(gist: GistEntity){
        viewModelScope.launch {
            gistFavoriteRepository.delete(gist)
        }
    }


    fun saveGist(gist: GistEntity){
        viewModelScope.launch {
            gistFavoriteRepository.save(gist)
        }
    }
}