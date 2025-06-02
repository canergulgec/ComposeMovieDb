package com.caner.testing

import com.caner.common.network.Resource
import com.caner.domain.usecase.SearchMovieUseCase
import com.caner.search.state.SearchUiState
import com.caner.search.state.TextEvent
import com.caner.search.vm.SearchViewModel
import com.caner.testing.data.TestData
import com.caner.testing.rules.MainDispatcherRule
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class SearchViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: SearchViewModel
    private val mockUseCase = mockk<SearchMovieUseCase>()

    @Before
    fun setup() {
        // For init block execution
        coEvery { mockUseCase.invoke(any()) } returns emptyFlow()
        viewModel = SearchViewModel(useCase = mockUseCase, sharingStarted = SharingStarted.Eagerly)
    }

    @Test
    fun `OnValueChange should update uiState with movies when useCase returns success`() = runTest {
        // Given
        val movieList = TestData.createMovieList()
        coEvery { mockUseCase.invoke(any()) } returns flow {
            emit(Resource.Loading(true))
            emit(Resource.Success(movieList))
            emit(Resource.Loading(false))
        }

        // When
        viewModel.onEvent(TextEvent.OnValueChange("query"))
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertThat(state is SearchUiState.HasMovies).isTrue()
        assertThat((state as SearchUiState.HasMovies).movies).isEqualTo(movieList.movies)
    }
}