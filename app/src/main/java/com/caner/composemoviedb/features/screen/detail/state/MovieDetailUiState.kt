package com.caner.composemoviedb.features.screen.detail.state

import com.caner.composemoviedb.data.model.MovieDetailModel
import com.caner.composemoviedb.utils.network.UserMessage

data class MovieDetailUiState(
    val movieDetailModel: MovieDetailModel? = null,
    val isFetchingMovieDetail: Boolean = false,
    val hasError: Boolean = false,
    val errorMessage: UserMessage? = null
)