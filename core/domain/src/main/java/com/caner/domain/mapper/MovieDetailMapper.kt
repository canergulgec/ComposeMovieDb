package com.caner.domain.mapper

import com.caner.common.utils.Mapper
import com.caner.model.MovieDetailModel
import com.caner.model.MovieImage
import com.caner.model.remote.MovieDetailResponse
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.math.roundToInt

class MovieDetailMapper @Inject constructor() : Mapper<MovieDetailResponse, MovieDetailModel> {

    override fun transform(from: MovieDetailResponse): MovieDetailModel = with(from) {
        MovieDetailModel(
            movieId = id,
            popularity = popularity,
            video = video,
            adult = adult,
            poster = poster_path?.let(::MovieImage),
            backdrop = backdrop_path?.let(::MovieImage),
            genres = genres.orEmpty(),
            title = title.orEmpty(),
            overview = overview.orEmpty(),
            imdbId = imdb_id,
            runtime = runtime,
            voteAverage = calculateVoteAverage(vote_average),
            voteCount = vote_count ?: 0,
            releaseDate = parseReleaseDate(release_date)
        )
    }

    private fun calculateVoteAverage(voteAverage: Double?): Double {
        return (voteAverage ?: 0.0).let { (it * 10).roundToInt() / 10.0 }
    }

    private fun parseReleaseDate(date: String?): Date? {
        return date?.let {
            if (it.isNotEmpty()) {
                SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).parse(it)
            } else {
                null
            }
        }
    }
}
