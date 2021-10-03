package com.example.gistproject.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gistproject.R
import com.example.gistproject.data.response.ResponseGist
import com.example.gistproject.presentation.fragment.ListenerGists
import de.hdodenhof.circleimageview.CircleImageView


class GitsAdapter(
   val context: Context,
   var listgist: MutableList<ResponseGist> = mutableListOf(),
   val listener: ListenerGists? = null
):  RecyclerView.Adapter<RecyclerView.ViewHolder>() {

   class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
      var imageUser: CircleImageView? = view.findViewById(R.id.circleImageView)
      var nameUser: TextView? = view.findViewById(R.id.txtName)
      var fileTypeUser: TextView? = view.findViewById(R.id.txtFileType)
      var favoriteButton: ImageView? = view.findViewById(R.id.imgFavorite)
   }

   override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
      val view = LayoutInflater.from(viewGroup.context)
         .inflate(R.layout.item_gist, viewGroup, false)

         return ViewHolder(view)
   }

   override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
      if(listgist[position].files.)
   }

   override fun getItemCount() = listgist.size

   }

}
