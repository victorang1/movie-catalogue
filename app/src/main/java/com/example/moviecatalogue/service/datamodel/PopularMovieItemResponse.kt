package com.example.moviecatalogue.service.datamodel

import com.google.gson.annotations.SerializedName

data class PopularMovieItemResponse(
    @SerializedName("poster_path")
    val posterPath: String? = "",
    val title: String? = "No title",
    val popularity: Double? = 0.0,
    @SerializedName("release_date")
    val releaseDate: String? = "",
    @SerializedName("vote_count")
    val voteCount: Long? = 0
)