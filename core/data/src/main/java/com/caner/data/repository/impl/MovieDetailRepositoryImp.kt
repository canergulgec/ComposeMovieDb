package com.caner.data.repository.impl

import com.caner.data.api.MovieApi
import com.caner.data.mapper.MovieDetailMapper
import com.caner.domain.repository.MovieDetailRepository
import com.caner.domain.model.MovieDetailModel
import javax.inject.Inject

class MovieDetailRepositoryImp @Inject constructor(
    private val api: MovieApi,
    private val mapper: MovieDetailMapper,
) : MovieDetailRepository {

    override suspend fun getMovieDetail(movieId: Int): MovieDetailModel {
        val response = api.getMovieDetail(movieId)
        return mapper.transform(response)
    }
}
