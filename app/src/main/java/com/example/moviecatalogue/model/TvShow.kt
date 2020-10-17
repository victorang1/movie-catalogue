package com.example.moviecatalogue.model

import android.os.Parcelable
import androidx.databinding.BaseObservable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow(
    private val tvImg: Int,
    private val tvTitle: String,
    private val tvYear: String,
    private val tvCategory: String,
    private val tvDuration: String,
    private val tvScore: Int,
    private val tvOverview: String,
) : BaseObservable(), Parcelable