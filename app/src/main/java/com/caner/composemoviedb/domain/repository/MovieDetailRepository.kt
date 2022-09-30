package com.caner.composemoviedb.domain.repository

import com.caner.composemoviedb.domain.model.remote.MovieDetailResponse

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: Int?): MovieDetailResponse
}