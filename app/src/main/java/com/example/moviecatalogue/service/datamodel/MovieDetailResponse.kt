package com.example.moviecatalogue.service.datamodel

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("poster_path")
    val posterPath: String? = "",
    val title: String? = "No title",
    val popularity: Double? = 0.0,
    val overview: String? = "",
    val release_date: String? = "",
    val genres: List<MovieGenresItemResponse>
)