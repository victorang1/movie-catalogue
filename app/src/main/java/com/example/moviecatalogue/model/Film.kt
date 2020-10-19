package com.example.moviecatalogue.model

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val image: String? = "",
    val title: String? = "",
    val popularity: Double? = 0.0,
    val voteCount: Long? = 0,
    val releaseDate: String? = "",
    val category: String? = "",
    val duration: String? = "",
    val overview: String? = "",
) : BaseObservable(), Parcelable {

    constructor(
        image: String,
        title: String,
        popularity: Double,
        voteCount: Long,
        releaseDate: String?
    ) : this(
        image,
        title,
        popularity,
        voteCount,
        releaseDate, "", "", ""
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