package com.caner.composemoviedb.domain.usecase

import com.caner.composemoviedb.domain.mapper.MovieDetailMapper
import com.caner.composemoviedb.data.model.MovieDetailModel
import com.caner.composemoviedb.data.repository.MovieDetailRepository
import com.caner.composemoviedb.utils.extension.mapTo
import com.caner.composemoviedb.utils.extension.onProgress
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val repository: MovieDetailRepository,
    private val mapper: MovieDetailMapper
) : BaseUseCase<MovieDetailModel, Int?>() {

    override fun buildResponse(params: Int?) = flow {
        val response = repository.getMovieDetail(params)
        emit(response.mapTo(mapper))
    }
        .onProgress()
}