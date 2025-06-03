package com.caner.search.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caner.common.network.Resource
import com.caner.domain.usecase.SearchMovieUseCase
import com.caner.search.state.SearchUiState
import com.caner.search.state.TextEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@HiltViewModel
class SearchViewModel @Inject constructor(private val useCase: SearchMovieUseCase) : ViewModel() {

    private val searchQuery = MutableStateFlow("")
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    init {
        initMovieSearch()
    }

    fun onEvent(event: TextEvent) {
        when (event) {
            is TextEvent.OnFocusChange -> {
                _uiState.update { it.copy(isHintVisible = event.isHintVisible) }
            }

            is TextEvent.OnValueChange -> {
                _uiState.update { it.copy(searchTitle = event.text) }
                searchQuery.value = event.text
            }
        }
    }

    private fun initMovieSearch() {
        viewModelScope.launch {
            searchQuery.debounce(400)
                .filter { it.length > 2 }
                .flatMapLatest { useCase.invoke(query = it) }
                .collect { resource ->
                    when (resource) {
                        is Resource.Success -> _uiState.update { it.copy(movies = resource.data.movies) }
                        is Resource.Loading -> _uiState.update { it.copy(isLoading = resource.status) }
                        is Resource.Error -> Timber.e(resource.error)
                    }
                }
        }
    }
}