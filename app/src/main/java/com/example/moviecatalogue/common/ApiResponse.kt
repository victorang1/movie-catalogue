package com.example.moviecatalogue.common

sealed class ApiResponse<T>(
    val body: T?,
    val message: String?
) {
    class Success<T>(body: T) : ApiResponse<T>(body, null)
    class Empty<T>(msg: String) : ApiResponse<T>(null, msg)
    class Error<T>(msg: String) : ApiResponse<T>(null, msg)
}