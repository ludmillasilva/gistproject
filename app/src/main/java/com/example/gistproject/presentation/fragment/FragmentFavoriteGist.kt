package com.example.gistproject.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.gistproject.R
import com.example.gistproject.data.response.Owner
import com.example.gistproject.data.response.ResponseGist
import com.example.gistproject.domain.GistParcelable
import com.example.gistproject.presentation.adapter.GistsAdapter

class FragmentFavoriteGist: Fragment(), ListenerGists {

    lateinit var gistsAdapter: GistsAdapter
    lateinit var idUser: TextView

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
        rvgists.adapter = gistsAdapter
    }

    override fun getGist(Gist: Int) {
        TODO("Not yet implemented")
    }

    override fun getDetailGist(gist: GistParcelable) {
        TODO("Not yet implemented")
    }

    override fun handleFavorite(gist: GistParcelable, isFavoriteGist: Boolean) {
        TODO("Not yet implemented")
    }



}

