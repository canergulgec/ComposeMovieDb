package com.caner.testing

import app.cash.turbine.test
import com.caner.common.network.Resource
import com.caner.domain.repository.MovieRepository
import com.caner.domain.mapper.MovieMapper
import com.caner.domain.usecase.HomeUseCase
import com.caner.model.MovieList
import com.caner.testing.data.TestData
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeUseCaseTest {

    private val mockRepository: MovieRepository = mockk()
    private val mockMapper: MovieMapper = mockk()
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var useCase: HomeUseCase

    @Before
    fun setup() {
        useCase = HomeUseCase(mockRepository, mockMapper, testDispatcher)
    }

    @Test
    fun `should emit loading and success when repository returns data`() = runTest(testDispatcher) {
        // Given
        val movieList = TestData.createMovieList()
        val movieResponse = TestData.createMovieResponse()

        coEvery { mockRepository.getPopularMovies() } returns movieResponse
        every { mockMapper.transform(movieResponse) } returns movieList

        // When
        val result = mutableListOf<Resource<MovieList>>()
        useCase.invoke().toList(result)
        advanceUntilIdle()

        // Then
        assertThat(result).hasSize(3)
        assertThat(result[0]).isInstanceOf(Resource.Loading::class.java)
        assertThat((result[0] as Resource.Loading).status).isTrue()

        assertThat(result[1]).isInstanceOf(Resource.Success::class.java)
        assertThat((result[1] as Resource.Success).data).isEqualTo(movieList)

        assertThat(result[2]).isInstanceOf(Resource.Loading::class.java)
        assertThat((result[2] as Resource.Loading).status).isFalse()
    }

    @Test
    fun `should emit loading and success when repository returns data using turbine`() = runTest(testDispatcher) {
        // Given
        val movieList = TestData.createMovieList()
        val movieResponse = TestData.createMovieResponse()

        coEvery { mockRepository.getPopularMovies() } returns movieResponse
        every { mockMapper.transform(movieResponse) } returns movieList

        // When & Then
        useCase.invoke().test {
            // First emission should be Loading(true)
            val loading = awaitItem()
            assertThat(loading).isInstanceOf(Resource.Loading::class.java)
            assertThat((loading as Resource.Loading).status).isTrue()

            // Second emission should be Success with movieModel
            val success = awaitItem()
            assertThat(success).isInstanceOf(Resource.Success::class.java)
            assertThat((success as Resource.Success).data).isEqualTo(movieList)

            // Third emission should be Loading(false)
            val loadingComplete = awaitItem()
            assertThat(loadingComplete).isInstanceOf(Resource.Loading::class.java)
            assertThat((loadingComplete as Resource.Loading).status).isFalse()

            // Flow should complete
            awaitComplete()
        }
    }
}


