package com.example.moviecatalogue.repository

import com.example.moviecatalogue.model.Film

interface ITvShowRepository {
    fun getTvShowData(): ArrayList<Film>
}