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

    override fun to(t: MovieDetailResponse): MovieDetailModel {
        return with(t) {
            MovieDetailModel(
                id,
                popularity,
                video,
                adult,
                poster_path?.let { path -> MovieImage(path) },
                backdrop_path?.let { path -> MovieImage(path) },
                genres ?: listOf(),
                title ?: "",
                overview ?: "",
                imdb_id,
                runtime,
                (vote_average ?: 0.0).let { (it * 10).roundToInt() / 10.0 },
                vote_count ?: 0,
                release_date?.let { date ->
                    if (date.isNotEmpty()) {
                        SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).parse(date)
                    } else {
                        null
                    }
                }
            )
        }
    }
}
