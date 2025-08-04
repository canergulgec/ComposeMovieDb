package com.caner.data.mapper

import com.caner.data.model.MovieListResponse
import com.caner.domain.model.Movie
import com.caner.domain.model.MovieImage
import com.caner.domain.model.MovieList
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject
import kotlin.math.roundToInt

class MovieMapper @Inject constructor() : Mapper<MovieListResponse, MovieList> {

    override fun transform(from: MovieListResponse) = with(from) {
        MovieList(
            total = total,
            page = page,
            movies = results.map {
                Movie(
                    movieId = it.id,
                    popularity = it.popularity,
                    poster = it.posterPath?.let { path -> MovieImage(path) },
                    backdrop = it.backdropPath?.let { path -> MovieImage(path) },
                    title = it.title,
                    voteAverage = (it.voteAverage * 10).roundToInt() / 10.0,
                    voteCount = it.voteCount ?: 0,
                    overview = it.overview,
                    releaseDate = it.releaseDate
                        ?.takeIf { date -> date.isNotBlank() }
                        ?.let { date ->
                            val parsedDate = LocalDate.parse(date)
                            parsedDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.getDefault()))
                        }
                )
            }
        )
    }
}
