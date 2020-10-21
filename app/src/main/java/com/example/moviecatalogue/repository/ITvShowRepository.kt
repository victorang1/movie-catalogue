package com.example.moviecatalogue.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.data.local.entity.Film

interface ITvShowRepository {
    fun getTvShowData(): LiveData<Resource<PagedList<Film>>>
    fun getTvDetails(tvId: Int): LiveData<Resource<Film>>
}