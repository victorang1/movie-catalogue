package com.example.moviecatalogue.data.local.entity

import androidx.annotation.NonNull
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.Entity

@Entity(primaryKeys = ["id", "title"])
data class Film(
    @NonNull
    val id: Int,
    val image: String? = "",
    @NonNull
    val title: String,
    val popularity: Double? = 0.0,
    val voteCount: Long? = 0,
    val releaseDate: String? = "",
    val category: String? = "",
    val overview: String? = "",
    val filmType: String
) : BaseObservable() {

    constructor(
        id: Int,
        image: String,
        title: String,
        popularity: Double,
        voteCount: Long,
        releaseDate: String?,
        filmType: String
    ) : this(
        id,
        image,
        title,
        popularity,
        voteCount,
        releaseDate, "", "", filmType
    )

    @Bindable
    fun getPopularityDisplay(): String {
        return popularity.toString()
    }

    @Bindable
    fun getVoteCountDisplay(): String {
        return voteCount.toString()
    }
}