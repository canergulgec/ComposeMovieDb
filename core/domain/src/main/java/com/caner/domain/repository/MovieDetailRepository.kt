package com.caner.domain.repository

import com.caner.model.MovieDetailModel

interface MovieDetailRepository {

    suspend fun getMovieDetail(movieId: Int): MovieDetailModel
}