package com.example.moviecatalogue.ui.tvshow

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.Film
import com.example.moviecatalogue.repository.ITvShowRepository

class TvShowViewModel(private val tvShowRepository: ITvShowRepository) : ViewModel() {

    fun getTvShowData(): ArrayList<Film> = tvShowRepository.getTvShowData()
}