package com.caner.detail.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caner.common.Constants
import com.caner.common.network.Resource
import com.caner.common.network.UserMessage
import com.caner.detail.state.MovieDetailUiState
import com.caner.domain.usecase.MovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val useCase: MovieDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState> = _uiState.asStateFlow()

    init {
        savedStateHandle.get<Int>(Constants.MOVIE_ID)?.let { movieId ->
            if (movieId != -1) {
                getMovieDetail(id = movieId)
            }
        }
    }

    private fun getMovieDetail(id: Int) {
        viewModelScope.launch {
            useCase.invoke(movieId = id).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _uiState.update { it.copy(movieDetailModel = resource.data) }
                    }

                    is Resource.Error -> {
                        _uiState.update { state ->
                            state.copy(
                                hasError = true,
                                errorMessage = UserMessage(resource.error.message)
                            )
                        }
                        Timber.e(resource.error)
                    }

                    is Resource.Loading -> {
                        _uiState.update { it.copy(isFetchingMovieDetail = resource.status) }
                    }
                }
            }
        }
    }
}
