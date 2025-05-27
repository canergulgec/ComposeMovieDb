package com.caner.domain.mapper

import com.caner.common.utils.Mapper
import com.caner.model.Movie
import com.caner.model.MovieImage
import com.caner.model.MovieModel
import com.caner.model.remote.MoviesResponse
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.math.roundToInt

class MovieMapper @Inject constructor() : Mapper<MoviesResponse, MovieModel> {

    override fun transform(from: MoviesResponse) = with(from) {
        MovieModel(
            total = total,
            page = page,
            movies = results.map {
                Movie(
                    movieId = it.id,
                    popularity = it.popularity,
                    video = it.video,
                    poster = it.posterPath?.let { path -> MovieImage(path) },
                    adult = it.adult,
                    backdrop = it.backdropPath?.let { path -> MovieImage(path) },
                    originalLanguage = it.originalLanguage,
                    originalTitle = it.originalTitle,
                    title = it.title,
                    voteAverage = (it.voteAverage * 10).roundToInt() / 10.0,
                    overview = it.overview,
                    releaseDate = it.releaseDate?.let { date ->
                        if (date.isNotEmpty()) {
                            val parsedDate =
                                SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).parse(
                                    date
                                ) ?: Date()
                            SimpleDateFormat("yyyy", Locale.getDefault()).format(parsedDate)
                        } else {
                            null
                        }
                    }
                )
            }
        )
    }
}
