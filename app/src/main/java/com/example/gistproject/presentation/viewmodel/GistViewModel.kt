package com.example.gistproject.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gistproject.data.factory.Network
import com.example.gistproject.data.model.GistEntity
import com.example.gistproject.data.repository.GistFavoriteRepository
import com.example.gistproject.data.repository.GistRepositoryImpl
import com.example.gistproject.data.response.Owner
import com.example.gistproject.data.response.ResponseGist
import com.example.gistproject.domain.GistParcelable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.launch

class GistViewModel: ViewModel() {
    private val gistRepository = GistRepositoryImpl()
    private val disposable = CompositeDisposable()

    val gistFavoriteRepository: GistFavoriteRepository = GistFavoriteRepository()
    val liveResponseGistParcelable: MutableLiveData<List<GistParcelable?>> = MutableLiveData<List<GistParcelable?>>()

    fun getGist(currentPage: Int){
        gistRepository.getGist(currentPage)
            .compose(Network.applySingleTransformer())
            .subscribe({
                liveResponseGistParcelable.value = it.map { gist->
                    GistParcelable.parserToGistFull(gist)
                }.toList()
            },{
                print(it.message)
            }).addToDispose()
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


    private fun Disposable.addToDispose(): Disposable = apply { disposable.add(this) }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }


}