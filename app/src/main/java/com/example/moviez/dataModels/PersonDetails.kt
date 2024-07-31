package com.example.moviez.dataModels

import com.google.gson.annotations.SerializedName

data class PersonDetails(
    @field:SerializedName("adult")
    val adult: Boolean = false,

    @field:SerializedName("biography")
    val biography: String = "",

    @field:SerializedName("birthday")
    val birthday: String = "",

    @field:SerializedName("deathday")
    val deathday: String = "",

    @field:SerializedName("gender")
    val gender: Int = 0,

    @field:SerializedName("id")
    val personId: Int = 0,

    @field:SerializedName("known_for_department")
    val department: String = "",

    @field:SerializedName("name")
    val name: String = "",

    @field:SerializedName("place_of_birth")
    val placeOfBirth: String = "",

    @field:SerializedName("profile_path")
    val profilePath: String = "",

    @field:SerializedName("images")
    val images: PersonImages ?= null,

    @field:SerializedName("movie_credits")
    val movieCredits: MovieCreditsOfPerson ?= null,

    @field:SerializedName("tv_credits")
    val tvCredits: TvCreditsOfPerson ?= null
)

data class PersonImages(
    @field:SerializedName("profiles")
    val profileImages: List<PersonImageListItem> = emptyList(),
)
data class PersonImageListItem(
    @field:SerializedName("file_path")
    val filePath: String = ""
)

data class MovieCreditsOfPerson(
    @field:SerializedName("cast")
    val asCast: List<MovieListItem> = emptyList(),

    @field:SerializedName("crew")
    val asCrew: List<MovieListItem> = emptyList(),
)

data class TvCreditsOfPerson(
    @field:SerializedName("cast")
    val asCast: List<SeriesListItem> = emptyList(),

    @field:SerializedName("crew")
    val asCrew: List<SeriesListItem> = emptyList(),
)