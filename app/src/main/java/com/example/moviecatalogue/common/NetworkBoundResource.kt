package com.example.moviecatalogue.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.Loading(null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDB()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    result.value = Resource.Success(newData)
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {

        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            result.value = Resource.Loading(newData)
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response) {
                is ApiResponse.Success ->
                    CoroutineScope(Dispatchers.IO).launch {
                        saveCallResult(response.body)
                        CoroutineScope(Dispatchers.Main).launch {
                            result.addSource(loadFromDB()) { newData ->
                                result.value = Resource.Success(newData)
                            }
                        }
                    }
                is ApiResponse.Empty -> CoroutineScope(Dispatchers.IO).launch {
                    result.addSource(loadFromDB()) { newData ->
                        result.value = Resource.Success(newData)
                    }
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.value = Resource.Error(newData, response.message)
                    }
                }
            }
        }
    }

    fun toLiveData(): LiveData<Resource<ResultType>> = result
}