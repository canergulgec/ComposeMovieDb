package com.caner.composemoviedb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caner.composemoviedb.domain.usecase.SearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@FlowPreview
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchMovieUseCase
) : ViewModel() {

    val searchQuery = MutableStateFlow("")

    val searchFlow = searchQuery
        .debounce(400)
        .filter { query ->
            return@filter query.length > 2
        }
        .flatMapLatest {
            searchUseCase.execute(it)
        }.shareIn(viewModelScope, SharingStarted.Lazily, replay = 1)
}