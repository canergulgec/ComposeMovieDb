package com.caner.composemoviedb.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caner.composemoviedb.utils.Constants
import com.caner.composemoviedb.utils.network.Resource
import com.caner.composemoviedb.utils.network.UserMessage
import com.caner.composemoviedb.domain.usecase.MovieDetailUseCase
import com.caner.composemoviedb.view.detail.state.MovieDetailUiState
import com.caner.composemoviedb.view.main.NavActions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val useCase: MovieDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState> = _uiState.asStateFlow()

    init {
        savedStateHandle.get<Int>(Constants.MOVIE_ID).let { movieId ->
            if (movieId != -1) {
                getMovieDetail(movieId)
            }
        }
    }

    private fun getMovieDetail(movieId: Int?) {
        viewModelScope.launch {
            useCase.execute(movieId).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(movieDetailModel = resource.data)
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(userMessages = UserMessage(message = resource.error.message))
                        }
                    }
                    is Resource.Loading -> {
                        _uiState.update {
                            it.copy(isFetchingMovieDetail = resource.status)
                        }
                    }
                }
            }
        }
    }

    fun userMessageShown() {
        _uiState.update { it.copy(userMessages = null) }
    }
}
