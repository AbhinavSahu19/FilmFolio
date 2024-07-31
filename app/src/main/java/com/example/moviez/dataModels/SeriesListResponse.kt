package com.example.moviez.dataModels

import com.google.gson.annotations.SerializedName

data class SeriesListResponse(
    @field:SerializedName("page")
    val pageNo : Int =0,

    @field:SerializedName("results")
    val results: List<SeriesListItem> = emptyList(),

    @field:SerializedName("total_pages")
    val totalPages: Int = 0,

    @field:SerializedName("total_results")
    val totalResults: Int =0
)
data class SeriesListItem(
    @field:SerializedName("id")
    val id: Int =0,

//    @field:SerializedName("original_name")
//    val seriesName: String = "",

    @field:SerializedName("poster_path")
    val posterPath : String = "",
)