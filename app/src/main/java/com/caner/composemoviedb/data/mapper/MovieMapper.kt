package com.caner.composemoviedb.data.mapper

import com.caner.composemoviedb.data.MovieModel
import com.caner.composemoviedb.data.remote.MovieResponseItem
import com.caner.composemoviedb.data.remote.MoviesResponse
import com.caner.composemoviedb.common.Mapper
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<MoviesResponse, MovieModel> {

    override fun from(e: MovieModel): MoviesResponse {
        return with(e) {
            MoviesResponse(
                total = total,
                page = page,
                results = e.movies.map {
                    MovieResponseItem(
                        it.movieId,
                        it.popularity,
                        it.video,
                        it.poster?.original,
                        it.adult,
                        it.backdrop?.original,
                        it.originalLanguage,
                        it.originalTitle,
                        it.title,
                        it.voteAverage,
                        it.overview,
                        it.releaseDate?.let { date ->
                            SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).format(
                                date
                            )
                        }
                    )
                }
            )
        }
    }

    override fun to(t: MoviesResponse): MovieModel {
        return with(t) {
            MovieModel(
                total = total,
                page = page,
                movies = results.map {
                    com.caner.composemoviedb.data.Movie(
                        it.id,
                        it.popularity,
                        it.video,
                        it.posterPath?.let { path -> com.caner.composemoviedb.data.MovieImage(path) },
                        it.adult,
                        it.backdropPath?.let { path -> com.caner.composemoviedb.data.MovieImage(path) },
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
