package com.caner.domain.mapper

import com.caner.model.MovieDetailModel
import com.caner.model.MovieImage
import com.caner.model.remote.MovieDetailResponse
import javax.inject.Inject
import kotlin.math.roundToInt

class MovieDetailMapper @Inject constructor() : Mapper<MovieDetailResponse, MovieDetailModel> {

    override fun transform(from: MovieDetailResponse): MovieDetailModel = with(from) {
        MovieDetailModel(
            movieId = id,
            popularity = popularity,
            video = video,
            adult = adult,
            poster = posterPath?.let(::MovieImage),
            backdrop = backdropPath?.let(::MovieImage),
            genres = genres.orEmpty(),
            title = title.orEmpty(),
            overview = overview.orEmpty(),
            imdbId = imdbId,
            runtime = runtime,
            voteAverage = calculateVoteAverage(voteAverage),
            voteCount = voteCount ?: 0,
            releaseDate = releaseDate
        )
    }

    private fun calculateVoteAverage(voteAverage: Double?): Double {
        return (voteAverage ?: 0.0).let { (it * 10).roundToInt() / 10.0 }
    }
}
