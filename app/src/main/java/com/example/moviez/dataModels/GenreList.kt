package com.example.moviez.dataModels

import com.google.gson.annotations.SerializedName

data class GenreList(
    @field:SerializedName("genres")
    val genres: List<GenreListItem> = emptyList()
)
data class GenreListItem(
    @field:SerializedName("id")
    val genreId: Int = 0,

    @field:SerializedName("name")
    val genreName: String =""
)