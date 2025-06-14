package com.caner.home.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caner.common.network.Resource
import com.caner.domain.usecase.HomeUseCase
import com.caner.home.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: HomeUseCase
) : ViewModel() {

    private val _movieUiState = MutableStateFlow(HomeUiState())
    val movieUiState: StateFlow<HomeUiState> = _movieUiState.asStateFlow()

    init {
        getPopularMovies()
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            useCase.invoke().collect { resource ->
                when (resource) {
                    is Resource.Success -> _movieUiState.update { it.copy(popularMovies = resource.data.movies) }
                    is Resource.Loading -> _movieUiState.update { it.copy(isLoading = resource.status) }
                    is Resource.Error -> _movieUiState.update { it.copy(error = resource.error.message) }
                }
            }
        }
    }
}