package com.example.moviecatalogue.common

sealed class ApiResponse<T>(
    val body: T,
    val message: String?
) {
    class Success<T>(body: T): ApiResponse<T>(body, null)
    class Empty<T>(body: T, msg: String) : ApiResponse<T>(body, msg)
    class Error<T>(body: T, msg: String) : ApiResponse<T>(body, msg)
}