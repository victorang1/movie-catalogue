package com.example.moviecatalogue.repository

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
import com.example.moviecatalogue.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception

class FavoriteRepository(private val localFavoriteSource: LocalFavoriteSource) :
    IFavoriteRepository {

    override fun getAllFavoriteMovies(): LiveData<Resource<PagedList<Favorite>>> {
        EspressoIdlingResource.increment()
        val result = MediatorLiveData<Resource<PagedList<Favorite>>>()
        result.value = Resource.Loading(null)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val fromDb = async(Dispatchers.IO) {
                    val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()
                    LivePagedListBuilder(localFavoriteSource.getAllMovies(), config).build()
                }
                result.addSource(fromDb.await()) {
                    result.removeSource(result)
                    result.postValue(Resource.Success(it))
                    EspressoIdlingResource.decrement()
                }
            } catch (e: Exception) {
                result.postValue(
                    Resource.Error(
                        null,
                        AppConstant.resources.getString(R.string.text_network_error)
                    )
                )
                EspressoIdlingResource.decrement()
            }
        }
        return result
    }

    override fun getAllFavoriteTvs(): LiveData<Resource<PagedList<Favorite>>> {
        EspressoIdlingResource.increment()
        val result = MediatorLiveData<Resource<PagedList<Favorite>>>()
        result.value = Resource.Loading(null)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val fromDb = async(Dispatchers.IO) {
                    val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()
                    LivePagedListBuilder(localFavoriteSource.getAllTvShows(), config).build()
                }
                result.addSource(fromDb.await()) {
                    result.removeSource(result)
                    result.postValue(Resource.Success(it))
                    EspressoIdlingResource.decrement()
                }
            } catch (e: Exception) {
                result.postValue(
                    Resource.Error(
                        null,
                        AppConstant.resources.getString(R.string.text_network_error)
                    )
                )
                EspressoIdlingResource.decrement()
            }
        }
        return result
    }

    override fun insertFavorite(favorite: Favorite): LiveData<Resource<Boolean>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<Resource<Boolean>>(Resource.Loading(null))
        CoroutineScope(Dispatchers.IO).launch {
            try {
                localFavoriteSource.insertFilm(favorite)
                result.postValue(Resource.Success(true))
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                val resources = Resource.Error(
                    false,
                    AppConstant.resources.getString(R.string.text_network_error)
                )
                result.postValue(resources)
                EspressoIdlingResource.decrement()
            }
        }
        return result
    }

    override fun getFavoriteMovieById(filmId: Int): LiveData<Resource<Favorite>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<Resource<Favorite>>(Resource.Loading(null))
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val movie = localFavoriteSource.getFavoriteMovieById(filmId)
                result.postValue(Resource.Success(movie))
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                val resources = Resource.Error<Favorite>(
                    null,
                    AppConstant.resources.getString(R.string.text_network_error)
                )
                result.postValue(resources)
                EspressoIdlingResource.decrement()
            }
        }
        return result
    }

    override fun getFavoriteTvShowById(filmId: Int): LiveData<Resource<Favorite>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<Resource<Favorite>>(Resource.Loading(null))
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val movie = localFavoriteSource.getFavoriteTvShowById(filmId)
                result.postValue(Resource.Success(movie))
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                val resources = Resource.Error<Favorite>(
                    null,
                    AppConstant.resources.getString(R.string.text_network_error)
                )
                result.postValue(resources)
                EspressoIdlingResource.decrement()
            }
        }
        return result
    }

    override fun deleteFromFavorite(favorite: Favorite): LiveData<Resource<Boolean>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<Resource<Boolean>>(Resource.Loading(null))
        CoroutineScope(Dispatchers.IO).launch {
            try {
                localFavoriteSource.deleteFromFavorite(favorite)
                result.postValue(Resource.Success(true))
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                val resources = Resource.Error(
                    false,
                    AppConstant.resources.getString(R.string.text_network_error)
                )
                result.postValue(resources)
                EspressoIdlingResource.decrement()
            }
        }
        return result
    }

    override fun isFavoriteMovie(movieId: Int): LiveData<Resource<Boolean>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<Resource<Boolean>>(Resource.Loading(null))
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val movie = localFavoriteSource.getFavoriteMovieById(movieId)
                result.postValue(Resource.Success(movie != null))
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                val resources = Resource.Error(
                    false,
                    AppConstant.resources.getString(R.string.text_network_error)
                )
                result.postValue(resources)
                EspressoIdlingResource.decrement()
            }
        }
        return result
    }

    override fun isFavoriteTvShow(tvId: Int): LiveData<Resource<Boolean>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<Resource<Boolean>>(Resource.Loading(null))
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val tvShow = localFavoriteSource.getFavoriteTvShowById(tvId)
                result.postValue(Resource.Success(tvShow != null))
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                result.postValue(
                    Resource.Error(
                        false,
                        AppConstant.resources.getString(R.string.text_network_error)
                    )
                )
                EspressoIdlingResource.decrement()
            }
        }
        return result
    }

    override fun searchMovies(title: String): LiveData<Resource<PagedList<Favorite>>> {
        EspressoIdlingResource.increment()
        val result = MediatorLiveData<Resource<PagedList<Favorite>>>()
        result.value = Resource.Loading(null)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val fromDb = async(Dispatchers.IO) {
                    val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()
                    LivePagedListBuilder(
                        localFavoriteSource.getFilteredMovies(title),
                        config
                    ).build()
                }
                result.addSource(fromDb.await()) {
                    result.removeSource(result)
                    result.postValue(Resource.Success(it))
                    EspressoIdlingResource.decrement()
                }
            } catch (e: Exception) {
                result.postValue(
                    Resource.Error(
                        null,
                        AppConstant.resources.getString(R.string.text_network_error)
                    )
                )
                EspressoIdlingResource.decrement()
            }
        }
        return result
    }

    override fun searchTvShows(title: String): LiveData<Resource<PagedList<Favorite>>> {
        EspressoIdlingResource.increment()
        val result = MediatorLiveData<Resource<PagedList<Favorite>>>()
        result.value = Resource.Loading(null)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val fromDb = async(Dispatchers.IO) {
                    val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()
                    LivePagedListBuilder(
                        localFavoriteSource.getFilteredTvShow(title),
                        config
                    ).build()
                }
                result.addSource(fromDb.await()) {
                    result.removeSource(result)
                    result.postValue(Resource.Success(it))
                    EspressoIdlingResource.decrement()
                }
            } catch (e: Exception) {
                result.postValue(
                    Resource.Error(
                        null,
                        AppConstant.resources.getString(R.string.text_network_error)
                    )
                )
                EspressoIdlingResource.decrement()
            }
        }
        return result
    }
}