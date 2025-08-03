package com.caner.testing

import app.cash.turbine.test
import com.caner.common.network.Resource
import com.caner.domain.repository.SearchRepository
import com.caner.domain.usecase.SearchMovieUseCase
import com.caner.testing.data.TestData
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SearchUseCaseTest {

    private val mockRepository: SearchRepository = mockk()
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var useCase: SearchMovieUseCase

    @Before
    fun setup() {
        useCase = SearchMovieUseCase(mockRepository, testDispatcher)
    }

    @Test
    fun `should emit loading and success when repository returns data`() = runTest(testDispatcher) {
        // Given
        val movieList = TestData.createMovieList()
        val query = "Spider"

        coEvery { mockRepository.searchMovie(query) } returns movieList

        // When & Then
        useCase.invoke(query).test {
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