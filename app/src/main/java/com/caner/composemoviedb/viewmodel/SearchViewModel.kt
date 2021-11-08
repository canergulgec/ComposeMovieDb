package com.caner.composemoviedb.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caner.composemoviedb.domain.usecase.SearchMovieUseCase
import com.caner.composemoviedb.ui.state.TextEvent
import com.caner.composemoviedb.ui.state.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@FlowPreview
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchMovieUseCase
) : ViewModel() {

    private val searchQuery = MutableStateFlow("")

    private val _searchContent = mutableStateOf(TextFieldState())
    val searchContent: State<TextFieldState> = _searchContent

    fun onEvent(event: TextEvent) {
        when (event) {
            is TextEvent.OnFocusChange -> {
                _searchContent.value = _searchContent.value.copy(isHintVisible = event.isHintVisible)
            }

            is TextEvent.OnValueChange -> {
                _searchContent.value = _searchContent.value.copy(text = event.text)
                searchQuery.value = event.text
            }
        }
    }

    val searchFlow = searchQuery
        .debounce(400)
        .filter { query ->
            return@filter query.length > 2
        }
        .flatMapLatest {
            searchUseCase.execute(it)
        }.shareIn(viewModelScope, SharingStarted.Lazily, replay = 1)
}