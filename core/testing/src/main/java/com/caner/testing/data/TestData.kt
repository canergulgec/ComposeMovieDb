package com.caner.testing.data

import com.caner.model.Movie
import com.caner.model.MovieImage
import com.caner.model.MovieModel

object TestData {
    private fun createMovie(movieId: Int = 1, title: String = "Test Movie", voteAverage: Double = 8.5) =
        Movie(
            movieId = movieId,
            popularity = 0.0,
            video = false,
            poster = MovieImage(""),
            adult = false,
            backdrop = MovieImage(""),
            originalLanguage = "",
            originalTitle = "",
            title = title,
            voteAverage = voteAverage,
            overview = "",
            releaseDate = null
        )

    fun createMovieModel(movies: List<Movie> = listOf(createMovie())) =
        MovieModel(
            total = movies.size,
            page = 0,
            movies = movies
        )
}