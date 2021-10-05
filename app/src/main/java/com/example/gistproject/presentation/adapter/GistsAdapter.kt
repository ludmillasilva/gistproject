package com.example.gistproject.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gistproject.R
import com.example.gistproject.data.response.ResponseGist
import com.example.gistproject.presentation.fragment.ListenerGists
import de.hdodenhof.circleimageview.CircleImageView


class GistsAdapter (
   val context: Context,
   var listgist: MutableList<ResponseGist> = mutableListOf(),
   val listener: ListenerGists? = null
):  RecyclerView.Adapter<GistsAdapter.ViewHolder>() {

   class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
      var imageUser: CircleImageView? = view.findViewById(R.id.circleImageView)
      var nameUser: TextView? = view.findViewById(R.id.txtName)
      var fileTypeUser: TextView? = view.findViewById(R.id.txtFileType)
      var favoriteButton: ImageView? = view.findViewById(R.id.imgFavorite)
   }

   override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
      val view = LayoutInflater.from(viewGroup.context)
         .inflate(R.layout.item_gist, viewGroup, false)

         return ViewHolder(view)
   }

   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      if(listgist[position].owner.avatar_url?.isNotBlank() == true) {
         holder.imageUser?.let { Glide.with(context).load((listgist[position].owner.avatar_url)).into(it) }
      }
      holder.nameUser?.text = listgist[position].owner.login
      holder.fileTypeUser?.text = listgist[position].files.map {file -> file.value.type }.toList().joinToString(", ")
      holder.imageUser?.setOnClickListener {
         listener?.getDetailGist(listgist[position].owner)
      }
   }

   override fun getItemCount() = listgist.size

}
