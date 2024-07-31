package com.example.moviez.dataModels

import com.google.gson.annotations.SerializedName

data class SeriesForSeasons(
    @field:SerializedName("seasons")
    val seasons: List<SeasonNumberClass> = emptyList(),
)
data class SeasonNumberClass(
    @field:SerializedName("season_number")
    val seasonNumber: Int =0,
)