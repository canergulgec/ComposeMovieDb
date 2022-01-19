package com.caner.composemoviedb.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.caner.composemoviedb.data.viewstate.Resource
import com.caner.composemoviedb.domain.repository.MovieRepository
import com.caner.composemoviedb.view.movie.state.MovieUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    init {
        getPopularMovies()
    }

    private val _popularMovieUiState =
        MutableStateFlow(MovieUiState(isFetchingPopularMovies = true))
    val popularMovieUiState: StateFlow<MovieUiState> = _popularMovieUiState.asStateFlow()
    val nowPlayingMoviesPagingFlow = movieRepository.getNowPlayingMovies().cachedIn(viewModelScope)

    private fun getPopularMovies() {
        viewModelScope.launch {
            movieRepository.getPopularMovies()
                .collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            _popularMovieUiState.update {
                                it.copy(
                                    popularMovies = resource.data.movies,
                                    isFetchingPopularMovies = false
                                )
                            }
                        }
                        else -> {
                            // Handle error state
                        }
                    }
                }
        }
    }
}