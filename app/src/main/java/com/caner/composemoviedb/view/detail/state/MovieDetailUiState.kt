package com.caner.composemoviedb.view.detail.state

import com.caner.composemoviedb.data.model.MovieDetailModel
import com.caner.composemoviedb.data.viewstate.UserMessage

data class MovieDetailUiState(
    val movieDetailModel: MovieDetailModel? = null,
    val isFetchingMovieDetail: Boolean = false,
    val userMessages: List<UserMessage> = emptyList()
)