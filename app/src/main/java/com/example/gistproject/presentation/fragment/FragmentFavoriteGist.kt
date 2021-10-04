package com.example.gistproject.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.gistproject.R
import com.example.gistproject.presentation.adapter.GistsAdapter

class FragmentFavoriteGist: Fragment(), ListenerGists {

    lateinit var gistsAdapter: GistsAdapter

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

    override fun getGists() {
        TODO("Not yet implemented")
    }
}

