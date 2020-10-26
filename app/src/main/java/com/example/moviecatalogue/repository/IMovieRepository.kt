package com.example.moviecatalogue.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.data.local.entity.Film

interface IMovieRepository {
    fun getMovieData(): LiveData<Resource<PagedList<Film>>>
    fun getMovieDetails(movieId: Int): LiveData<Resource<Film>>
}