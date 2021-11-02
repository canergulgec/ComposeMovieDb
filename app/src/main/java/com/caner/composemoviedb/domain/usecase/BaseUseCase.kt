package com.caner.composemoviedb.domain.usecase

import com.caner.composemoviedb.data.viewstate.ApiError
import com.caner.composemoviedb.data.viewstate.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

/**
 * Base UseCase class
 * [M] -> Response Model Object
 * [Params] -> Request Parameters
 */
abstract class BaseUseCase<M, Params> {

    abstract fun buildRequest(params: Params?): Flow<Resource<M>>

    fun execute(params: Params? = null) =
        buildRequest(params).catch { error ->
            emit(Resource.Error(ApiError(4, error.message)))
        }
}