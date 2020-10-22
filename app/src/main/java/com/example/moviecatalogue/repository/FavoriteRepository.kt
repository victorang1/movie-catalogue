package com.example.moviecatalogue.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecatalogue.R
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.constant.AppConstant
import com.example.moviecatalogue.data.local.LocalFavoriteSource
import com.example.moviecatalogue.data.local.entity.Favorite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class FavoriteRepository(private val localFavoriteSource: LocalFavoriteSource) :
    IFavoriteRepository {

    override fun getAllFavoriteMovies(): LiveData<Resource<PagedList<Favorite>>> {
        val result = MediatorLiveData<Resource<PagedList<Favorite>>>()
        result.value = Resource.Loading(null)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                val fromDb =
                    LivePagedListBuilder(localFavoriteSource.getAllMovies(), config).build()
                result.addSource(fromDb) {
                    result.removeSource(result)
                    result.postValue(Resource.Success(it))
                }
            } catch (e: Exception) {
                result.postValue(
                    Resource.Error(
                        null,
                        AppConstant.resources.getString(R.string.text_network_error)
                    )
                )
            }
        }
        return result
    }

    override fun getAllFavoriteTvs(): LiveData<Resource<PagedList<Favorite>>> {
        val result = MediatorLiveData<Resource<PagedList<Favorite>>>()
        result.value = Resource.Loading(null)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                val fromDb =
                    LivePagedListBuilder(localFavoriteSource.getAllTvShows(), config).build()
                result.addSource(fromDb) {
                    result.removeSource(result)
                    result.postValue(Resource.Success(it))
                }
            } catch (e: Exception) {
                result.postValue(
                    Resource.Error(
                        null,
                        AppConstant.resources.getString(R.string.text_network_error)
                    )
                )
            }
        }
        return result
    }

    override fun insertFavorite(favorite: Favorite): LiveData<Resource<Boolean>> {
        val result = MutableLiveData<Resource<Boolean>>(Resource.Loading(null))
        CoroutineScope(Dispatchers.IO).launch {
            try {
                localFavoriteSource.insertFilm(favorite)
                result.postValue(Resource.Success(true))
            } catch (e: Exception) {
                val resources = Resource.Error(
                    false,
                    AppConstant.resources.getString(R.string.text_network_error)
                )
                result.postValue(resources)
            }
        }
        return result
    }

    override fun deleteFromFavorite(favorite: Favorite): LiveData<Resource<Boolean>> {
        val result = MutableLiveData<Resource<Boolean>>(Resource.Loading(null))
        CoroutineScope(Dispatchers.IO).launch {
            try {
                localFavoriteSource.deleteFromFavorite(favorite)
                result.postValue(Resource.Success(true))
            } catch (e: Exception) {
                val resources = Resource.Error(
                    false,
                    AppConstant.resources.getString(R.string.text_network_error)
                )
                result.postValue(resources)
            }
        }
        return result
    }

    override fun isFavoriteMovie(movieId: Int): LiveData<Resource<Boolean>> {
        val result = MutableLiveData<Resource<Boolean>>(Resource.Loading(null))
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val movie = localFavoriteSource.isFavoriteMovie(movieId)
                result.postValue(Resource.Success(movie != null))
            } catch (e: Exception) {
                val resources = Resource.Error(
                    false,
                    AppConstant.resources.getString(R.string.text_network_error)
                )
                result.postValue(resources)
            }
        }
        return result
    }

    override fun isFavoriteTvShow(tvId: Int): LiveData<Resource<Boolean>> {
        val result = MutableLiveData<Resource<Boolean>>(Resource.Loading(null))
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val tvShow = localFavoriteSource.isFavoriteTvShow(tvId)
                result.postValue(Resource.Success(tvShow != null))
            } catch (e: Exception) {
                result.postValue(Resource.Error(
                    false,
                    AppConstant.resources.getString(R.string.text_network_error)
                ))
            }
        }
        return result
    }
}