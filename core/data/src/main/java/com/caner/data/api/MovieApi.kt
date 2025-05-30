package com.caner.data.api

import com.caner.common.utils.HttpParams
import com.caner.common.utils.HttpRoutes
import com.caner.model.remote.MovieDetailResponse
import com.caner.model.remote.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET(HttpRoutes.POPULAR_MOVIES)
    suspend fun getPopularMovies(): MovieListResponse

    @GET(HttpRoutes.MOVIE_DETAIL)
    suspend fun getMovieDetail(@Path(HttpParams.MOVIE_ID) movieId: Int): MovieDetailResponse

    @GET(HttpRoutes.SEARCH_MOVIE)
    suspend fun searchMovie(@Query(HttpParams.QUERY) query: String): MovieListResponse
} 