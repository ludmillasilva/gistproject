package com.example.gistproject.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.gistproject.DetailsActivity
import com.example.gistproject.R
import com.example.gistproject.data.model.GistEntity
import com.example.gistproject.domain.GistParcelable
import com.example.gistproject.presentation.adapter.GistsAdapter
import com.example.gistproject.presentation.viewmodel.GistViewModel
import androidx.recyclerview.widget.LinearLayoutManager

class FragmentListGist: Fragment(), ListenerGists {

    lateinit var gistsAdapter: GistsAdapter
    lateinit var gistsViewModel: GistViewModel
    lateinit var searchGist: SearchView
    lateinit var loadCircle: RelativeLayout

    var pageCount = 1
    var pastVisibleItems  = 0
    var visibleItemCount = 0
    var totalItemCount  = 0
    lateinit var rvLayoutManager: LinearLayoutManager
    private var loading = true


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
        searchGist = view.findViewById<SearchView>(R.id.searchGist)
        gistsViewModel = ViewModelProvider(requireActivity()).get(GistViewModel::class.java)
        gistsAdapter = GistsAdapter(context = view.context, listener = this)
        rvgists.adapter = gistsAdapter


        rvLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvgists.setLayoutManager(rvLayoutManager)

        rvgists.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    visibleItemCount = rvLayoutManager.getChildCount()
                    totalItemCount = rvLayoutManager.getItemCount()
                    pastVisibleItems = rvLayoutManager.findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                            loading = false
                            if(gistsViewModel.liveResponseSearch.value.isNullOrEmpty()){
                                gistsViewModel.getGist(pageCount + 1,::loadCircle)
                            }
                            pageCount++
                            loading = true
                        }
                    }
                }
            }
        })

        initRequests()
        initObservers()
        addActions()
    }

    override fun onResume() {
        super.onResume()
        clearData()
    }

    fun clearData(){
        gistsAdapter.listgist.clear()
        gistsViewModel.liveResponseSearch.value = listOf()
        gistsViewModel.liveResponseSearch.removeObservers(this)
        gistsViewModel.liveResponseGistParcelable.removeObservers(this)
        gistsViewModel.getGist(1,::loadCircle)
        gistObserver()
    }


    fun addActions(){
        searchGist.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchGist.clearFocus()
                query?.let{
                    gistsViewModel.getSearchGist(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                if(newText?.isNotBlank()== true){
//                    Handler(Looper.getMainLooper()).postDelayed({
//                        newText.let{
//                            gistsViewModel.getSearchGist(newText)
//                        }
//                    }, AUTO_SEARCH_TIME)
//                }else{
//                    clearData()
//                }
                return false
            }
        })

        searchGist.setOnCloseListener {
            clearData()
            searchGist.setQuery("",false)
            searchGist.clearFocus()
            false
        }

        val searchCloseButtonId: Int = searchGist.context.resources
            .getIdentifier("android:id/search_close_btn", null, null)
        val closeBtn:ImageView = searchGist.findViewById(searchCloseButtonId);
        closeBtn.setOnClickListener {
            clearData()
            searchGist.setQuery("",false)
            searchGist.clearFocus()
            false
        }
    }


    fun loadCircle(showLoading:Boolean) {
        val view: View? = activity?.findViewById(R.id.loadingPanel)
        view.let {
            if(view is RelativeLayout&&showLoading){
                view.visibility = View.VISIBLE
                view.bringToFront()
                view.invalidate()
            }else{
                view?.visibility = View.GONE
            }
        }

    }

    fun initRequests() {
        gistsViewModel.getGist(pageCount,::loadCircle)
    }

    fun initObservers() {
        gistObserver()
        gistSearchObserver()
    }

    fun gistObserver() {
        gistsViewModel.liveResponseGistParcelable.observe(viewLifecycleOwner,{ gist ->
            gist?.let {
                //gistsAdapter.listgist.clear()
                gistsAdapter.listgist.addAll(it)
                gistsAdapter.notifyDataSetChanged()
            }

        })
    }

    fun gistSearchObserver() {
        gistsViewModel.liveResponseSearch.observe(viewLifecycleOwner,{ gist ->
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
            gist.type,
            gist.filename
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

    companion object {
        private const val AUTO_SEARCH_TIME = 1500L
    }
}