package com.caner.composemoviedb.utils.network

sealed class Resource<out T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error(val error: Throwable) : Resource<Nothing>()
    data class Loading(val status: Boolean) : Resource<Nothing>()
}