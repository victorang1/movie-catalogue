package com.example.moviecatalogue.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviecatalogue.data.local.entity.Film

@Dao
interface FilmDao {

    @Query("SELECT * FROM film WHERE filmType=:filmType")
    fun getAllFilmsByType(filmType: String): DataSource.Factory<Int, Film>

    @Query("SELECT * FROM film WHERE id=:id AND filmType=:filmType")
    fun getFilmById(id: Int, filmType: String): LiveData<Film>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilm(films: List<Film>)

    @Insert
    fun insertFilm(film: Film)
}