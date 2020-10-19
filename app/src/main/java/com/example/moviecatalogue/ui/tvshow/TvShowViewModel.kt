package com.example.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.Film
import com.example.moviecatalogue.repository.ITvShowRepository

class TvShowViewModel(private val tvShowRepository: ITvShowRepository) : ViewModel() {

    private val isLoading = MutableLiveData<Boolean>()

    fun getTvShowData(): LiveData<List<Film>> = tvShowRepository.getTvShowData()

    fun setLoading(loading: Boolean) {
        isLoading.value = loading
    }

    fun getLoadingStatus(): LiveData<Boolean> = isLoading
}