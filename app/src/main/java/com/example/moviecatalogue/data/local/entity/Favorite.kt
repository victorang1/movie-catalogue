package com.example.moviecatalogue.data.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Film::class,
        parentColumns = ["id"],
        childColumns = ["filmId"],
    )],
)
data class Favorite(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    var favoriteId: Int,
    
    @NonNull
    var filmId: Int,

    @NonNull
    var filmType: String,

    @NonNull
    var title: String,

    @NonNull
    var category: String
)