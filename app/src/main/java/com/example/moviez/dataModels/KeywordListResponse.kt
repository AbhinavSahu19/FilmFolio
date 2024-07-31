package com.example.moviez.dataModels

import com.google.gson.annotations.SerializedName

data class KeywordListResponse(
//    @field:SerializedName("page")
//    val pageNo : Int = 0,

    @field:SerializedName("results")
    val results: List<Keyword> = emptyList(),

//    @field:SerializedName("total_pages")
//    val totalPages: Int = 0,
//
//    @field:SerializedName("total_results")
//    val totalResults: Int =0
)

data class Keyword(
//    @field:SerializedName("id")
//    val id: Int =0,

    @field:SerializedName("name")
    val name: String = ""
)
