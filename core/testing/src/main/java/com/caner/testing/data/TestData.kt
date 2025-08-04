package com.caner.testing.data

import com.caner.domain.model.Movie
import com.caner.domain.model.MovieImage
import com.caner.domain.model.MovieList

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
}