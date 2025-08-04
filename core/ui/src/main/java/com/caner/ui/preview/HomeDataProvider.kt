package com.caner.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.caner.domain.model.Movie
import com.caner.domain.model.MovieImage

class HomeDataProvider : PreviewParameterProvider<List<Movie>> {
    override val values: Sequence<List<Movie>>
        get() = sequenceOf(
            listOf(
                Movie(
                    movieId = 1,
                    poster = MovieImage("/wKiOkZTN9lUUUNZLmtnwubZYONg.jpg"),
                    title = "Movie Title 1",
                    voteAverage = 7.8,
                    voteCount = 1000,
                    overview = "Short overview 1",
                    popularity = 1234.0,
                    backdrop = MovieImage("/nmGWzTLMXy9x7mKd8NKPLmHtWGa.jpg"),
                    releaseDate = "2023-01-01"
                ),
                Movie(
                    movieId = 2,
                    poster = MovieImage("/wKiOkZTN9lUUUNZLmtnwubZYONg.jpg"),
                    title = "Movie Title 2",
                    voteAverage = 8.2,
                    voteCount = 1000,
                    overview = "Short overview 2",
                    popularity = 1100.0,
                    backdrop = MovieImage("/nmGWzTLMXy9x7mKd8NKPLmHtWGa.jpg"),
                    releaseDate = "2023-02-02"
                ),
                Movie(
                    movieId = 3,
                    poster = MovieImage("/wKiOkZTN9lUUUNZLmtnwubZYONg.jpg"),
                    title = "Movie Title 3 with a very long name to test ellipsis",
                    voteAverage = 6.5,
                    voteCount = 1000,
                    overview = "Short overview 3",
                    popularity = 950.0,
                    backdrop = MovieImage("/nmGWzTLMXy9x7mKd8NKPLmHtWGa.jpg"),
                    releaseDate = "2023-03-03"
                )
            )
        )
} 