package com.example.moviez.dataModels

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @field:SerializedName("page")
    val pageNo : Int=0,

    @field:SerializedName("results")
    val results: List<MovieListItem> = emptyList(),

    @field:SerializedName("total_pages")
    val totalPages: Int =0,

    @field:SerializedName("total_results")
    val totalResults: Int =0
)

data class MovieListItem(
    @field:SerializedName("id")
    val id: Int=0,

//    @field:SerializedName("original_title")
//    val movieName: String ="",

    @field: SerializedName("poster_path")
    val posterPath: String =""
)
