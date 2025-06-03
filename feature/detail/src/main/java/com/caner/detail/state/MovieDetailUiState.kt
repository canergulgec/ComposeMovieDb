package com.caner.detail.state

import com.caner.common.network.UserMessage
import com.caner.model.MovieDetailModel

data class MovieDetailUiState(
    val movieDetailData: MovieDetailModel? = null,
    val isFetchingMovieDetail: Boolean = false,
    val hasError: Boolean = false,
    val errorMessage: UserMessage? = null
)