package com.example.gistproject.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.gistproject.DetailsActivity
import com.example.gistproject.R
import com.example.gistproject.data.model.GistEntity
import com.example.gistproject.data.response.ResponseGist
import com.example.gistproject.domain.GistParcelable
import com.example.gistproject.presentation.adapter.GistsAdapter
import com.example.gistproject.presentation.viewmodel.GistViewModel

class FragmentListGist: Fragment(), ListenerGists {

    lateinit var gistsAdapter: GistsAdapter
    lateinit var gistsViewModel: GistViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvgists = view.findViewById<RecyclerView>(R.id.rvGist)
        gistsViewModel = ViewModelProvider(requireActivity()).get(GistViewModel::class.java)
        gistsAdapter = GistsAdapter(context = view.context, listener = this)
        rvgists.adapter = gistsAdapter
        initRequests()
        initObservers()

    }
    fun initRequests() {
        gistsViewModel.getGist()
    }

    fun initObservers() {
        gistObserver()
    }

    fun gistObserver() {
        gistsViewModel.liveResponseGistParcelable.observe(viewLifecycleOwner,{ gist ->
            gist?.let {
                gistsAdapter.listgist.clear()
                gistsAdapter.listgist.addAll(it)
                gistsAdapter.notifyDataSetChanged()
            }

        })
    }


    override fun getDetailGist(gist: GistParcelable){
        val detailGistId = Intent (requireContext(),DetailsActivity::class.java)
        detailGistId.putExtra("BASE_URL", gist)
        startActivity(detailGistId)
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
            gistsViewModel.saveGist(gistEntity)
        }else{
            gistsViewModel.deleteGist(gistEntity)
        }
    }


    override fun getGist(Gist: Int) {
        val castGistId = Intent (requireContext(), DetailsActivity::class.java)
        castGistId.putExtra("BASE_URL", Gist)
        startActivity(castGistId)
    }
}