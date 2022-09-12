package com.caner.domain.mapper

import com.caner.common.utils.Mapper
import com.caner.data.model.Movie
import com.caner.data.model.MovieImage
import com.caner.data.model.MovieModel
import com.caner.data.model.remote.MovieResponseItem
import com.caner.data.model.remote.MoviesResponse
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

    fun toMovie(t: MovieResponseItem): Movie {
        return with(t) {
            Movie(
                id,
                popularity,
                video,
                posterPath?.let { path -> MovieImage(path) },
                adult,
                backdropPath?.let { path -> MovieImage(path) },
                originalLanguage,
                originalTitle,
                title,
                voteAverage,
                overview,
                releaseDate?.let { date ->
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
    }
}
