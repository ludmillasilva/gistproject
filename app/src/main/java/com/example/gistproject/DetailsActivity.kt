package com.example.gistproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.gistproject.domain.GistConfig
import com.example.gistproject.presentation.adapter.GistsAdapter
import com.example.gistproject.presentation.viewmodel.GistViewModel
import de.hdodenhof.circleimageview.CircleImageView


class DetailsActivity : AppCompatActivity() {

    private lateinit var imgUser: CircleImageView
    private lateinit var nameUser: TextView
    private lateinit var gistsAdapter: GistsAdapter
    private val gistViewModel = GistViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        imgUser = findViewById(R.id.circleImageView)
        nameUser = findViewById(R.id.txtName)


        val gist: GistConfig? = intent.extras?.getParcelable("BASE_URL")

        Glide.with(this).load((gist?.avatar_url)).into(imgUser)
        nameUser.text = gist?.login
        /*gist?.let{
            gistViewModel.getGist(it)
        }*/

    }
}