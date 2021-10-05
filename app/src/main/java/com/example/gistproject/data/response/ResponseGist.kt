package com.example.gistproject.data.response

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import okhttp3.Response

class ResponseGist (
    @SerializedName("id")
    val id: String,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("files")
    @Expose
    val files: Map<String,File>,
    var isFavorite: Boolean


)