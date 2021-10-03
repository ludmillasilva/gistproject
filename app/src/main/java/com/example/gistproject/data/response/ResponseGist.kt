package com.example.gistproject.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseGist (
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("files")
    @Expose
    val files: Map<String,File>
)