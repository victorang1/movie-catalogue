package com.example.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.data.local.entity.Favorite
import com.example.moviecatalogue.data.local.entity.Film
import com.example.moviecatalogue.repository.IFavoriteRepository
import com.example.moviecatalogue.repository.IMovieRepository
import com.example.moviecatalogue.repository.ITvShowRepository

class FakeFilmDetailViewModel(
    private val movieRepository: IMovieRepository,
    private val tvShowRepository: ITvShowRepository,
    private val favoriteRepository: IFavoriteRepository
) : ViewModel() {

    fun loadDataById(id: Int, type: String): LiveData<Resource<Film>> {
        return when (type) {
            "Movie" -> movieRepository.getMovieDetails(id)
            else -> tvShowRepository.getTvDetails(id)
        }
    }

    fun isFavoriteFilm(id: Int, type: String): LiveData<Resource<Boolean>> {
        return when (type) {
            "Movie" -> favoriteRepository.isFavoriteMovie(id)
            else -> favoriteRepository.isFavoriteTvShow(id)
        }
    }

    fun addToFavorite(favorite: Favorite): LiveData<Resource<Boolean>> =
        favoriteRepository.insertFavorite(favorite)
}