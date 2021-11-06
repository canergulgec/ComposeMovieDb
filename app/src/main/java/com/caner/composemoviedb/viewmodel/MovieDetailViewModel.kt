package com.caner.composemoviedb.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caner.composemoviedb.data.Constants
import com.caner.composemoviedb.data.viewstate.Resource
import com.caner.composemoviedb.data.model.MovieDetailModel
import com.caner.composemoviedb.domain.usecase.MovieDetailUseCase
import com.caner.composemoviedb.ui.view.NavActions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailUseCase: MovieDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movieDetailState = MutableStateFlow<Resource<MovieDetailModel>>(Resource.Initial)
    val movieDetailState = _movieDetailState.asStateFlow()

    lateinit var navActions: NavActions

    init {
        savedStateHandle.get<Int>(Constants.MOVIE_ID).let { movieId ->
            if (movieId != -1) {
                getMovieDetail(movieId)
            }
        }
    }

    private fun getMovieDetail(movieId: Int?) {
        viewModelScope.launch {
            movieDetailUseCase.execute(movieId)
                .collect {
                    _movieDetailState.value = it
                }
        }
    }
}
