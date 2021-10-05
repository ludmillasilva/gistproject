package com.example.gistproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.gistproject.domain.GistParcelable
import com.example.gistproject.presentation.adapter.GistsAdapter
import com.example.gistproject.presentation.viewmodel.GistViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.hdodenhof.circleimageview.CircleImageView


class DetailsActivity : AppCompatActivity() {

    private lateinit var imgUser: CircleImageView
    private lateinit var nameUser: TextView
    private lateinit var fileName: TextView
    private lateinit var gistsAdapter: GistsAdapter
    private val gistViewModel = GistViewModel()
    private lateinit var returnbtn: FloatingActionButton
    private lateinit var idUser: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        imgUser = findViewById(R.id.circleImageView)
        nameUser = findViewById(R.id.txtName)
        returnbtn = findViewById(R.id.fltReturnBtn)
        fileName = findViewById(R.id.txtFilename)
        idUser = findViewById(R.id.txtId)


        val gistParcelable: GistParcelable? = intent.extras?.getParcelable("BASE_URL")

        Glide.with(this).load((gistParcelable?.avatar_url)).into(imgUser)
        nameUser.text = gistParcelable?.login
        fileName.text = gistParcelable?.filename
        idUser.text = gistParcelable?.id

        /*gist?.let{
            gistViewModel.getGist(it)
        }*/


        returnbtn.setOnClickListener{
            finish()
        }

    }
}