package com.caner.common.extension

import com.caner.common.network.Resource
import kotlinx.coroutines.flow.*

fun <T> Flow<Resource<T>>.withLoading() =
    onStart {
        emit(Resource.Loading(true))
    }.onCompletion {
        emit(Resource.Loading(false))
    }

fun <T> Flow<Resource<T>>.catchNetworkError() =
    catch { error ->
        emit(Resource.Error(Throwable(message = error.message)))
        emit(Resource.Loading(false))
    }