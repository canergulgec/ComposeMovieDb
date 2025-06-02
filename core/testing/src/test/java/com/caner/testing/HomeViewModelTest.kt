package com.caner.testing

import com.caner.common.network.Resource
import com.caner.domain.usecase.HomeUseCase
import com.caner.home.vm.HomeViewModel
import com.caner.testing.data.TestData
import com.caner.testing.rules.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: HomeViewModel
    private val mockUseCase = mockk<HomeUseCase>()

    @Before
    fun setup() {
        // For init block execution
        coEvery { mockUseCase.invoke() } returns emptyFlow()
        viewModel = HomeViewModel(mockUseCase)
    }

    @Test
    fun `when useCase returns success, should update state with movies`() = runTest {
        // Given
        val movieList = TestData.createMovieList()
        coEvery { mockUseCase.invoke() } returns flow {
            emit(Resource.Loading(true))
            emit(Resource.Success(movieList))
            emit(Resource.Loading(false))
        }

        // When
        viewModel.getPopularMovies()
        advanceUntilIdle()

        // Then
        val state = viewModel.movieUiState.value
        assertEquals(movieList.movies, state.popularMovies)
        assertTrue(!state.isLoading)
    }

    @Test
    fun `useCase should return error`() = runTest {
        // Given
        val error = Throwable("Test error")
        coEvery { mockUseCase.invoke() } returns flow {
            emit(Resource.Loading(true))
            emit(Resource.Error(error = error))
            emit(Resource.Loading(false))
        }

        // When
        viewModel.getPopularMovies()
        advanceUntilIdle()

        // Then
        val state = viewModel.movieUiState.value
        assertEquals(error.message, state.error)
        assertTrue(!state.isLoading)
    }
} 