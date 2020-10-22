package com.example.moviecatalogue.data.local.entity

import androidx.annotation.NonNull
import androidx.room.*

@Entity(tableName = "favorite",
    primaryKeys = ["favoriteId", "filmId"],
    foreignKeys = [ForeignKey(entity = Film::class,
        parentColumns = ["id"],
        childColumns = ["filmId"])],
    indices = [Index(value = ["favoriteId"]),
        Index(value = ["filmId"])])
data class Favorite(

    @NonNull
    @ColumnInfo(name = "favoriteId")
    val favoriteId: String,

    @NonNull
    @ColumnInfo(name = "filmId")
    val filmId: Int,

    @NonNull
    val filmType: String,

    val image: String,

    val title: String,

    val category: String
)