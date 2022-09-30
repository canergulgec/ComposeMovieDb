package com.caner.composemoviedb.data.provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.caner.composemoviedb.domain.model.MovieDetailModel
import com.caner.composemoviedb.domain.model.MovieImage
import com.caner.composemoviedb.domain.model.remote.MovieGenre

class MovieDetailDataProvider : PreviewParameterProvider<MovieDetailModel> {
    override val values: Sequence<MovieDetailModel>
        get() = sequenceOf(
            MovieDetailModel(
                movieId = 438148,
                popularity = 3865.589,
                video = false,
                adult = false,
                poster = MovieImage("/wKiOkZTN9lUUUNZLmtnwubZYONg.jpg"),
                backdrop = MovieImage("/nmGWzTLMXy9x7mKd8NKPLmHtWGa.jpg"),
                genres = listOf(
                    MovieGenre(id = 10751, name = "Family"),
                    MovieGenre(id = 16, name = "Animation"),
                    MovieGenre(id = 12, name = "Adventure"),
                    MovieGenre(id = 35, name = "Comedy"),
                ),
                title = "Minions: The Rise of Gru",
                overview = "A fanboy of a supervillain supergroup known as the Vicious 6, Gru hatches a plan to become evil enough to join them, with the backup of his followers, the Minions.",
                voteAverage = 8.0,
                voteCount = 79,
                runtime = 87
            )
        )
}