package com.example.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.Film
import com.example.moviecatalogue.repository.ITvShowRepository

class TvShowViewModel(val tvShowRepository: ITvShowRepository) : ViewModel() {

    fun getTvShowData(): LiveData<ArrayList<Film>> {
        val tvShows = MutableLiveData<ArrayList<Film>>()
        tvShows.value = tvShowRepository.getTvShowData()
        return tvShows
    }
}