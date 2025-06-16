package com.caner.testing.data

import com.caner.model.Movie
import com.caner.model.MovieImage
import com.caner.model.MovieList
import com.caner.model.remote.MovieDto
import com.caner.model.remote.MovieListResponse

object TestData {
    private fun createMovie(movieId: Int = 1, title: String = "Test Movie", voteAverage: Double = 8.5) =
        Movie(
            movieId = movieId,
            popularity = 0.0,
            poster = MovieImage(""),
            backdrop = MovieImage(""),
            title = title,
            voteAverage = voteAverage,
            overview = "",
            voteCount = 100,
            releaseDate = null
        )

    fun createMovieList(movies: List<Movie> = listOf(createMovie())) =
        MovieList(
            total = movies.size,
            page = 0,
            movies = movies
        )

    fun createMovieResponse() =
        MovieListResponse(
            total = 15,
            page = 2,
            results = createMovieResponseItemList()
        )

    private fun createMovieResponseItemList() =
        listOf(
            MovieDto(
                id = 1,
                popularity = 0.0,
                posterPath = "",
                backdropPath = "",
                title = "Test Movie 1",
                voteAverage = 8.5,
                overview = "",
                releaseDate = ""
            )
        )
}