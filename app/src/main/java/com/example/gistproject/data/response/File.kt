package com.example.gistproject.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class File {
    @SerializedName("type")
    @Expose
    val type: String? = null
}