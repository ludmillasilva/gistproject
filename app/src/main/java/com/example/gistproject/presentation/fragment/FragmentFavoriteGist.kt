package com.example.gistproject.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.gistproject.R
import com.example.gistproject.data.model.GistEntity
import com.example.gistproject.domain.GistParcelable
import com.example.gistproject.presentation.adapter.GistsAdapter
import com.example.gistproject.presentation.viewmodel.GistFavoriteViewModel

class FragmentFavoriteGist: Fragment(), ListenerGists {

    lateinit var gistsAdapter: GistsAdapter
    lateinit var idUser: TextView
    lateinit var gistFavoriteViewModel: GistFavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_gist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvgists = view.findViewById<RecyclerView>(R.id.rvGist)
        gistsAdapter = GistsAdapter(context = view.context, listener = this)
        gistFavoriteViewModel = ViewModelProvider(requireActivity()).get(GistFavoriteViewModel::class.java)
        rvgists.adapter = gistsAdapter
        initRequests()
        initObservers()
    }

    fun initRequests(){
        gistFavoriteViewModel.getAllGist()
    }


    fun initObservers(){
        gistObserver()
    }

    fun gistObserver(){
        gistFavoriteViewModel.liveGist.observe(viewLifecycleOwner,{ gist ->
            gist?.let {
                val gistList : List<GistParcelable> = it.map {
                    GistParcelable( id = it.id,
                        login = it.login,
                        avatar_url = it.avatar_url,
                        type = it.filetype,
                        isFavorite = true)
                }
                gistsAdapter.listgist.clear()
                gistsAdapter.listgist.addAll(gistList)
                gistsAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun getGist(Gist: Int) {
        TODO("Not yet implemented")
    }

    override fun getDetailGist(gist: GistParcelable) {
        TODO("Not yet implemented")
    }

    override fun handleFavorite(gist: GistParcelable, isFavoriteGist: Boolean) {
        var id:String = ""
        gist.id.let {
            if (it != null) {
                id = it
            }
        }
        val gistEntity = GistEntity(
            id,
            gist.login,
            gist.avatar_url,
            gist.type
        )
        if(isFavoriteGist){
            gistFavoriteViewModel.saveGist(gistEntity)
        }else{
            gistFavoriteViewModel.deleteGist(gistEntity)
        }
    }



}

