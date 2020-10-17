package com.example.moviecatalogue.model

import android.os.Parcelable
import androidx.databinding.BaseObservable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val image: Int,
    val title: String,
    val year: String,
    val category: String,
    val duration: String,
    val score: Int,
    val overview: String,
) : BaseObservable(), Parcelable