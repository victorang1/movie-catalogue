package com.example.moviecatalogue.service

interface ApiHandler<T> {
    fun onSuccess(response: T)
    fun onFailure(throwable: Throwable)
}