package com.caner.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.caner.domain.model.Movie
import com.caner.domain.model.MovieImage

class SearchScreenDataProvider : PreviewParameterProvider<List<Movie>> {
    override val values: Sequence<List<Movie>>
        get() = sequenceOf(
            listOf(
                Movie(
                    movieId = 438148,
                    popularity = 3865.589,
                    poster = MovieImage("/wKiOkZTN9lUUUNZLmtnwubZYONg.jpg"),
                    backdrop = MovieImage("/nmGWzTLMXy9x7mKd8NKPLmHtWGa.jpg"),
                    title = "Minions: The Rise of Gru",
                    overview = "A fanboy of a supervillain supergroup known as the Vicious 6, Gru hatches a plan to become evil enough to join them, with the backup of his followers, the Minions.",
                    voteAverage = 8.0,
                    voteCount = 1000,
                    releaseDate = "2022"
                ),
                Movie(
                    movieId = 438149,
                    popularity = 3865.589,
                    poster = MovieImage("/wKiOkZTN9lUUUNZLmtnwubZYONg.jpg"),
                    backdrop = MovieImage("/nmGWzTLMXy9x7mKd8NKPLmHtWGa.jpg"),
                    title = "Minions: The Rise of Gru",
                    overview = "A fanboy of a supervillain supergroup known as the Vicious 6, Gru hatches a plan to become evil enough to join them, with the backup of his followers, the Minions.",
                    voteAverage = 8.0,
                    voteCount = 1000,
                    releaseDate = "2022"
                ),
                Movie(
                    movieId = 438150,
                    popularity = 3865.589,
                    poster = MovieImage("/wKiOkZTN9lUUUNZLmtnwubZYONg.jpg"),
                    backdrop = MovieImage("/nmGWzTLMXy9x7mKd8NKPLmHtWGa.jpg"),
                    title = "Minions: The Rise of Gru",
                    overview = "A fanboy of a supervillain supergroup known as the Vicious 6, Gru hatches a plan to become evil enough to join them, with the backup of his followers, the Minions.",
                    voteAverage = 8.0,
                    voteCount = 1000,
                    releaseDate = "2022"
                ),
                Movie(
                    movieId = 438151,
                    popularity = 3865.589,
                    poster = MovieImage("/wKiOkZTN9lUUUNZLmtnwubZYONg.jpg"),
                    backdrop = MovieImage("/nmGWzTLMXy9x7mKd8NKPLmHtWGa.jpg"),
                    title = "Minions: The Rise of Gru",
                    overview = "A fanboy of a supervillain supergroup known as the Vicious 6, Gru hatches a plan to become evil enough to join them, with the backup of his followers, the Minions.",
                    voteAverage = 8.0,
                    voteCount = 1000,
                    releaseDate = "2022"
                ),
                Movie(
                    movieId = 438152,
                    popularity = 3865.589,
                    poster = MovieImage("/wKiOkZTN9lUUUNZLmtnwubZYONg.jpg"),
                    backdrop = MovieImage("/nmGWzTLMXy9x7mKd8NKPLmHtWGa.jpg"),
                    title = "Minions: The Rise of Gru",
                    overview = "A fanboy of a supervillain supergroup known as the Vicious 6, Gru hatches a plan to become evil enough to join them, with the backup of his followers, the Minions.",
                    voteAverage = 8.0,
                    voteCount = 1000,
                    releaseDate = "2022"
                ),
                Movie(
                    movieId = 438153,
                    popularity = 3865.589,
                    poster = MovieImage("/wKiOkZTN9lUUUNZLmtnwubZYONg.jpg"),
                    backdrop = MovieImage("/nmGWzTLMXy9x7mKd8NKPLmHtWGa.jpg"),
                    title = "Minions: The Rise of Gru",
                    overview = "A fanboy of a supervillain supergroup known as the Vicious 6, Gru hatches a plan to become evil enough to join them, with the backup of his followers, the Minions.",
                    voteAverage = 8.0,
                    voteCount = 1000,
                    releaseDate = "2022"
                )
            )
        )
}