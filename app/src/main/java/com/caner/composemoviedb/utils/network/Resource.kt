package com.caner.composemoviedb.utils.network

sealed class Resource<out T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error(val apiError: ApiError) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}