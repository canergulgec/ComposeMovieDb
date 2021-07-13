package com.caner.composemoviedb.presentation

import androidx.lifecycle.ViewModel
import com.caner.composemoviedb.domain.usecase.SearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@FlowPreview
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchMovieUseCase
) : ViewModel() {

    val searchQuery = MutableSharedFlow<String>(replay = 0)

    @ExperimentalCoroutinesApi
    val searchFlow = searchQuery
        .debounce(400)
        .filter { query ->
            return@filter query.length > 2
        }
        .flatMapLatest {
            searchUseCase.execute(it)
        }
}