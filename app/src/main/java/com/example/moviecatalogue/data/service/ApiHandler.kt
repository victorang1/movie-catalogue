package com.example.moviecatalogue.data.service

interface ApiHandler<T> {
    fun onSuccess(response: T)
    fun onFailure(throwable: Throwable)
}