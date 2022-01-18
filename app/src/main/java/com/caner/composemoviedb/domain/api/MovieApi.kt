package com.caner.composemoviedb.domain.api

import com.caner.composemoviedb.data.model.remote.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MovieApi {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@QueryMap params: HashMap<String, Any>?): MoviesResponse
}