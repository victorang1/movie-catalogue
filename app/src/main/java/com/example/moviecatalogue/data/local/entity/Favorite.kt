package com.example.moviecatalogue.data.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    primaryKeys = ["favoriteId", "filmId", "title"],
    foreignKeys = [ForeignKey(
        entity = Film::class,
        parentColumns = ["id"],
        childColumns = ["filmId"],
    )],
)
data class Favorite(
    @NonNull
    var filmId: Int,

    @NonNull
    @PrimaryKey(autoGenerate = true)
    var favoriteId: Int,

    @NonNull
    var filmType: String,

    @NonNull
    var title: String,

    @NonNull
    var category: String
)