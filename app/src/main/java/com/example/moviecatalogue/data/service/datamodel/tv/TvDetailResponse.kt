package com.example.moviecatalogue.data.service.datamodel.tv

import com.example.moviecatalogue.data.service.datamodel.GenresItemResponse
import com.google.gson.annotations.SerializedName

data class TvDetailResponse(
    val id: Int = 0,
    @SerializedName("poster_path")
    val posterPath: String? = "",
    @SerializedName("name")
    val title: String? = "No title",
    val popularity: Double? = 0.0,
    val overview: String? = "",
    @SerializedName("first_air_date")
    val release_date: String? = "",
    val genres: List<GenresItemResponse>,
    @SerializedName("vote_count")
    val voteCount: Long? = 0,
)