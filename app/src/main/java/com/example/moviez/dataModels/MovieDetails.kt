package com.example.moviez.dataModels

import com.google.gson.annotations.SerializedName

data class MovieDetails (
    @field:SerializedName("adult")
    val adult: Boolean = false,

    @field:SerializedName("budget")
    val budget: Long =0,

    @field:SerializedName("backdrop_path")
    val backdropPath: String = "",

    @field:SerializedName("genres")
    val genres: List<GenreListItem> = emptyList(),

    @field:SerializedName("id")
    val movieId: Int =0,

    @field:SerializedName("original_language")
    val originalLanguage: String ="",

    @field:SerializedName("original_title")
    val originalTitle: String = "",

    @field:SerializedName("overview")
    val overview: String = "",

    @field:SerializedName("popularity")
    val popularity: Double = 0.0,

    @field:SerializedName("poster_path")
    val posterPath: String = "",

    @field:SerializedName("release_date")
    val releaseDate: String = "",

    @field:SerializedName("revenue")
    val revenue: Long =0,

    @field:SerializedName("runtime")
    val runtime: Int = 0,

    @field:SerializedName("status")
    val status: String = "",

    @field:SerializedName("tagline")
    val tagline: String = "",

    @field:SerializedName("title")
    val title: String = "",

    @field:SerializedName("vote_average")
    val rating: Double = 0.0,

    @field:SerializedName("vote_count")
    val voteCount: Int = 0,

    @field:SerializedName("credits")
    val credits: Credits ?= null,

    @field:SerializedName("images")
    val images: MovieImages ?= null,

    @field:SerializedName("similar")
    val similar: MovieListResponse ?= null,

    @field:SerializedName("translations")
    val translations: Translations ?= null,
)

data class MovieImages(
    @field:SerializedName("backdrops")
    val backdrops: List<BackdropListItem> = emptyList(),
)
data class BackdropListItem(
    @field:SerializedName("file_path")
    val backdropPath: String = "",
)


