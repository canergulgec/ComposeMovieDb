package com.caner.composemoviedb.features.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caner.composemoviedb.utils.network.Resource
import com.caner.composemoviedb.domain.usecase.SearchMovieUseCase
import com.caner.composemoviedb.features.screen.search.state.SearchViewModelState
import com.caner.composemoviedb.features.screen.search.state.TextEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: SearchMovieUseCase
) : ViewModel() {

    private val searchQuery = MutableStateFlow("")
    private val viewModelState = MutableStateFlow(SearchViewModelState())

    // UI state exposed to the UI
    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        initSearch()
    }

    fun onEvent(event: TextEvent) {
        when (event) {
            is TextEvent.OnFocusChange -> {
                viewModelState.update { it.copy(isHintVisible = event.isHintVisible) }
            }

            is TextEvent.OnValueChange -> {
                viewModelState.update { it.copy(title = event.text) }
                searchQuery.value = event.text
            }
        }
    }

    private fun initSearch() {
        viewModelScope.launch {
            searchQuery.debounce(400)
                .filter { query ->
                    return@filter query.length > 2
                }
                .flatMapLatest { query ->
                    useCase.searchMovie(query = query)
                }
                .collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            viewModelState.update { it.copy(movies = resource.data.movies) }
                        }
                        is Resource.Loading -> {
                            viewModelState.update { it.copy(isLoading = resource.status) }
                        }
                        is Resource.Error -> {
                            // Handle error state
                        }
                    }
                }
        }
    }
}