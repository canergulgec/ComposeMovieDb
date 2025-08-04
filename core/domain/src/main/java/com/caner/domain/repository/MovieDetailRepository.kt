package com.caner.domain.repository

import com.caner.domain.model.MovieDetailModel

interface MovieDetailRepository {

    suspend fun getMovieDetail(movieId: Int): MovieDetailModel
}