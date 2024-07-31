package com.example.moviez.dataModels

import com.google.gson.annotations.SerializedName

data class PersonListResponse(
    @field:SerializedName("page")
    val pageNo : Int = 0,

    @field:SerializedName("results")
    val results: List<PersonListItem> = emptyList(),

    @field:SerializedName("total_pages")
    val totalPages: Int = 0,

    @field:SerializedName("total_results")
    val totalResults: Int = 0
)
data class PersonListItem(
    @field:SerializedName("id")
    val id: Int = 0,

//    @field:SerializedName("original_name")
//    val personName: String = "",

    @field:SerializedName("profile_path")
    val posterPath : String = "",
)
