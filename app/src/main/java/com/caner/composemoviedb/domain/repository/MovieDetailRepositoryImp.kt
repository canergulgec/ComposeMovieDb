package com.caner.composemoviedb.domain.repository

import com.caner.composemoviedb.common.Mapper
import com.caner.composemoviedb.common.ext.filterMapperResponse
import com.caner.composemoviedb.data.MovieDetailModel
import com.caner.composemoviedb.data.remote.MovieDetailResponse
import com.caner.composemoviedb.domain.api.MovieDetailApi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailRepositoryImp @Inject constructor(
    private val apiService: MovieDetailApi,
    private val movieDetailMapper: Mapper<MovieDetailResponse, MovieDetailModel>,
) : MovieDetailRepository {

    override fun getMovieDetail(movieId: Int?) = flow {
        val data = apiService.getMovieDetail(movieId)
        emit(data.filterMapperResponse(movieDetailMapper))
    }
}
