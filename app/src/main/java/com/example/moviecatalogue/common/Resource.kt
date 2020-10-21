package com.example.moviecatalogue.common

sealed class Resource<T>(
    val data: T?,
    val message: String?
) {
    class Success<T>(data: T) : Resource<T>(data, null)
    class Error<T>(data: T?, msg: String?) : Resource<T>(data, msg)
    class Loading<T>(data: T?) : Resource<T>(data, null)
}