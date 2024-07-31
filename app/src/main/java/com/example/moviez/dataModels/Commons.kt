package com.example.moviez.dataModels

import com.google.gson.annotations.SerializedName

data class Translations(
    @field:SerializedName("translations")
    val translations: List<TranslationListItem> = emptyList(),
)
data class TranslationListItem(
    @field:SerializedName("iso_639_1")
    val id: String ="",

    @field:SerializedName("english_name")
    val englishName: String  ="",
)


data class Credits(
    @field:SerializedName("cast")
    val cast: List<CastListItem> = emptyList(),
)
data class CastListItem(
    @field:SerializedName("id")
    val castId: Int = 0,

    @field:SerializedName("name")
    val castName: String = "",

    @field:SerializedName("character")
    val castCharacter: String = "",

    @field:SerializedName("profile_path")
    val castProfilePath: String = "",
)