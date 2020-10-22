package com.example.moviecatalogue.data.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.example.moviecatalogue.data.local.entity.Favorite

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite where filmType=:filmType")
    fun getAllFavoritesByType(filmType: String): DataSource.Factory<Int, Favorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteFromFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorite WHERE filmType=:filmType AND filmId=:id LIMIT 1")
    suspend fun isFavoriteFilm(id: Int, filmType: String): Favorite
}