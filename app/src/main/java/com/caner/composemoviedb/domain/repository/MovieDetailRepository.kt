package com.caner.composemoviedb.domain.repository

import com.caner.composemoviedb.data.viewstate.Resource
import com.caner.composemoviedb.data.model.MovieDetailModel
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    fun getMovieDetail(movieId: Int?): Flow<Resource<MovieDetailModel>>
}