package com.caner.composemoviedb.domain.usecase

import com.caner.composemoviedb.utils.network.ApiError
import com.caner.composemoviedb.utils.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

/**
 * Base UseCase class
 * [M] -> Response Model Object
 * [Params] -> Request Parameters
 */
abstract class BaseUseCase<M, Params> {

    abstract fun buildResponse(params: Params?): Flow<Resource<M>>

    fun execute(params: Params? = null) =
        buildResponse(params).catch { error ->
            emit(Resource.Error(ApiError(4, error.message)))
        }
            .flowOn(Dispatchers.IO)
}