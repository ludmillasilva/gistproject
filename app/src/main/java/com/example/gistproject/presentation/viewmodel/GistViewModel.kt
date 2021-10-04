package com.example.gistproject.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gistproject.data.factory.Network
import com.example.gistproject.data.repository.GistRepositoryImpl
import com.example.gistproject.data.response.Owner
import com.example.gistproject.data.response.ResponseGist
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class GistViewModel: ViewModel() {
    private val gistRepository = GistRepositoryImpl()
    private val disposable = CompositeDisposable()


    val liveResponseGist: MutableLiveData<List<ResponseGist>> = MutableLiveData<List<ResponseGist>>()

    fun getGist(){
        gistRepository.getGist()
            .compose(Network.applySingleTransformer())
            .subscribe({
                liveResponseGist.value = it
            },{
                print(it.message)
            }).addToDispose()
    }

    private fun Disposable.addToDispose(): Disposable = apply { disposable.add(this) }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

}