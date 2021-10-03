package com.example.gistproject.data.response

import com.google.gson.annotations.SerializedName

class Owner (
    @SerializedName("login")
    val login: String? = null,
    @SerializedName("avatar_url")
    val avatar_url: String? = null,
)