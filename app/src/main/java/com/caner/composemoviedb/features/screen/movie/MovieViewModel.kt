package com.caner.composemoviedb.features.screen.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.caner.composemoviedb.utils.network.Resource
import com.caner.composemoviedb.domain.usecase.MovieUseCase
import com.caner.composemoviedb.features.screen.movie.state.MovieUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val useCase: MovieUseCase
) : ViewModel() {

    private val _movieUiState = MutableStateFlow(MovieUiState(isFetchingMovies = true))
    val movieUiState: StateFlow<MovieUiState> = _movieUiState.asStateFlow()

    init {
        getPopularMovies()
        getNowPlayingMovies()
    }

    private fun getNowPlayingMovies() {
        val moviesPagingFlow = useCase.getNowPlayingMovies().cachedIn(scope = viewModelScope)
        _movieUiState.update { it.copy(nowPlayingMovies = moviesPagingFlow) }
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            useCase.getPopularMovies().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _movieUiState.update {
                            it.copy(popularMovies = resource.data.movies)
                        }
                    }
                    is Resource.Loading -> _movieUiState.update { it.copy(isFetchingMovies = resource.status) }
                    is Resource.Error -> {
                    }
                }
            }
        }
    }
}