package com.caner.data.mapper

import com.caner.data.model.MovieDetailResponse
import com.caner.domain.model.MovieDetailModel
import com.caner.domain.model.MovieGenre
import com.caner.domain.model.MovieImage
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
            genres = genres?.map { MovieGenre(id = it.id, name = it.name) },
            title = title.orEmpty(),
            overview = overview.orEmpty(),
            imdbId = imdbId,
            runtime = runtime,
            voteAverage = (voteAverage ?: 0.0).let { (it * 10).roundToInt() / 10.0 },
            voteCount = voteCount ?: 0,
            releaseDate = releaseDate
        )
    }
}
