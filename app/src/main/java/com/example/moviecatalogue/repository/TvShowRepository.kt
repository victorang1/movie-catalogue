package com.example.moviecatalogue.repository

import com.example.moviecatalogue.model.Film

class TvShowRepository : ITvShowRepository {

    override fun getTvShowData(): ArrayList<Film> {
        return arrayListOf()
    }
}