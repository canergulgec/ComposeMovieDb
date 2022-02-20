package com.caner.composemoviedb.data.repository

import com.caner.composemoviedb.data.model.remote.MovieDetailResponse

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: Int?): MovieDetailResponse
}