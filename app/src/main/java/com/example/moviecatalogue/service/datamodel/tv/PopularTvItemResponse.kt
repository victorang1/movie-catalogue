package com.example.moviecatalogue.service.datamodel.tv

import com.google.gson.annotations.SerializedName

data class PopularTvItemResponse(
    @SerializedName("poster_path")
    val posterPath: String? = "",
    @SerializedName("name")
    val title: String? = "No title",
    val popularity: Double? = 0.0,
    @SerializedName("first_air_date")
    val releaseDate: String? = "",
    @SerializedName("vote_count")
    val voteCount: Long? = 0
)