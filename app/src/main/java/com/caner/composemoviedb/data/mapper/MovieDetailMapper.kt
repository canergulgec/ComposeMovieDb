package com.caner.composemoviedb.data.mapper

import com.caner.composemoviedb.data.model.MovieDetailModel
import com.caner.composemoviedb.data.model.MovieImage
import com.caner.composemoviedb.data.model.remote.MovieDetailResponse
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MovieDetailMapper @Inject constructor() :
    Mapper<MovieDetailResponse, MovieDetailModel> {

    override fun from(e: MovieDetailModel): MovieDetailResponse {
        return with(e) {
            MovieDetailResponse(
                movieId,
                popularity,
                video,
                adult,
                backdrop?.original,
                poster?.original,
                genres,
                title,
                overview,
                imdbId,
                runtime,
                voteAverage,
                voteCount,
                releaseDate?.let { date ->
                    SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).format(date)
                }
            )
        }
    }

    override fun to(t: MovieDetailResponse): MovieDetailModel {
        return with(t) {
            MovieDetailModel(
                id,
                popularity,
                video,
                adult,
                poster_path?.let { path -> MovieImage(path) },
                backdrop_path?.let { path -> MovieImage(path) },
                genres,
                title ?: "",
                overview ?: "",
                imdb_id,
                runtime,
                vote_average ?: 0.0,
                vote_count ?: 0,
                release_date?.let { date ->
                    if (date.isNotEmpty()) {
                        SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).parse(
                            date
                        )
                    } else {
                        null
                    }
                }
            )
        }
    }
}
