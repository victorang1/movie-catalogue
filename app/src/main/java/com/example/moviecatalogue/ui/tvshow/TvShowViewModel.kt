package com.example.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.repository.ITvShowRepository

class TvShowViewModel(val tvShowRepository: ITvShowRepository) : ViewModel() {

    fun getMovieData(): LiveData<ArrayList<TvShow>> {
        val tvShows = MutableLiveData<ArrayList<TvShow>>()
        tvShows.value = tvShowRepository.getTvShowData()
        return tvShows
    }
}