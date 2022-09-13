package com.caner.home.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.caner.common.network.Resource
import com.caner.domain.usecase.HomeUseCase
import com.caner.home.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: HomeUseCase
) : ViewModel() {

    private val _movieUiState = MutableStateFlow(HomeUiState(isFetchingMovies = true))
    val movieUiState: StateFlow<HomeUiState> = _movieUiState.asStateFlow()

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
                    is Resource.Error -> Timber.e(resource.error)
                }
            }
        }
    }
}