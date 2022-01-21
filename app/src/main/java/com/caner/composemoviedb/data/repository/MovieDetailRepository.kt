package com.caner.composemoviedb.data.repository

import com.caner.composemoviedb.utils.network.Resource
import com.caner.composemoviedb.data.model.remote.MovieDetailResponse

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: Int?): Resource<MovieDetailResponse>
}