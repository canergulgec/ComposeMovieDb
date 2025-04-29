package com.caner.domain.mapper

import com.caner.common.utils.Mapper
import com.caner.model.Movie
import com.caner.model.MovieImage
import com.caner.model.MovieModel
import com.caner.model.remote.MoviesResponse
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<MoviesResponse, MovieModel> {

    override fun to(t: MoviesResponse): MovieModel {
        return with(t) {
            MovieModel(
                total = total,
                page = page,
                movies = results.map {
                    Movie(
                        it.id,
                        it.popularity,
                        it.video,
                        it.posterPath?.let { path -> MovieImage(path) },
                        it.adult,
                        it.backdropPath?.let { path -> MovieImage(path) },
                        it.originalLanguage,
                        it.originalTitle,
                        it.title,
                        it.voteAverage,
                        it.overview,
                        it.releaseDate?.let { date ->
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
}
