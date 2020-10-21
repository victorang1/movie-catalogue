package com.example.moviecatalogue.data.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(

    @PrimaryKey
    @NonNull
    val favoriteId: String,

    val filmType: String,

    val title: String,

    val category: String
)