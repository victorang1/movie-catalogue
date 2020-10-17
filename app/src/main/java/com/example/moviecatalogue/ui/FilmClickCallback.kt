package com.example.moviecatalogue.ui

import com.example.moviecatalogue.model.Film

interface FilmClickCallback {
    fun onItemClick(film: Film)
}