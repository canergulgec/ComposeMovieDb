package com.caner.composemoviedb.domain.repository

import com.caner.composemoviedb.common.Resource
import com.caner.composemoviedb.data.MovieDetailModel
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    fun getMovieDetail(movieId: Int?): Flow<Resource<MovieDetailModel>>
}