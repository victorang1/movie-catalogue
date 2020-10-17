package com.example.moviecatalogue.model

import android.os.Parcelable
import androidx.databinding.BaseObservable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    private val movieImg: Int,
    private val movieTitle: String,
    private val movieYear: String,
    private val movieCategory: String,
    private val movieDuration: String,
    private val movieScore: Int,
    private val movieOverview: String,
    private val movieLanguage: String,
    private val movieBudget: String,
    private val movieRevenue: String
) : BaseObservable(), Parcelable