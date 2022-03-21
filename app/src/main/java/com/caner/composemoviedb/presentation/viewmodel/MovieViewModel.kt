package com.caner.composemoviedb.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.caner.composemoviedb.utils.network.Resource
import com.caner.composemoviedb.domain.usecase.MovieUseCase
import com.caner.composemoviedb.view.movie.state.MovieUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val useCase: MovieUseCase
) : ViewModel() {

    init {
        getPopularMovies()
    }

    private val _popularMovieUiState =
        MutableStateFlow(MovieUiState(isFetchingMovies = true))
    val popularMovieUiState: StateFlow<MovieUiState> = _popularMovieUiState.asStateFlow()

    val nowPlayingMoviesPagingFlow = useCase.getNowPlayingMovies()
        .cachedIn(viewModelScope)

    private fun getPopularMovies() {
        viewModelScope.launch {
            useCase.execute().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _popularMovieUiState.update {
                            it.copy(
                                popularMovies = resource.data.movies,
                                isFetchingMovies = false
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _popularMovieUiState.update {
                            it.copy(
                                isFetchingMovies = true
                            )
                        }
                    }
                    is Resource.Error -> {}
                }
            }
        }
    }
}