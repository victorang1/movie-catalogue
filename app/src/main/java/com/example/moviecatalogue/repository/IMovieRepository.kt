package com.example.moviecatalogue.repository

import com.example.moviecatalogue.model.Film

interface IMovieRepository {
    fun getMovieData(): ArrayList<Film>
}