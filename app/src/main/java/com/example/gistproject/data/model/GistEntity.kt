package com.example.gistproject.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gist")

data class GistEntity (
    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "login")
    val login: String? = null,

    @ColumnInfo(name = "avatar_url")
    val avatar_url: String? = null,

    @ColumnInfo(name = "filetype")
    val filetype: String? = null,


    )

