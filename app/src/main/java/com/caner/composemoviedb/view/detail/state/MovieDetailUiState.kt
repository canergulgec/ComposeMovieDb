package com.caner.composemoviedb.view.detail.state

import com.caner.composemoviedb.data.model.MovieDetailModel
import com.caner.composemoviedb.utils.network.UserMessage

data class MovieDetailUiState(
    val movieDetailModel: MovieDetailModel? = null,
    val isFetchingMovieDetail: Boolean = false,
    val userMessages: UserMessage? = null
)