package com.caner.composemoviedb.utils.network

object HttpRoutes {
    const val BASE_URL = "api.themoviedb.org/3"
    const val MOVIE_DETAIL = "movie/{movie_id}"
    const val SEARCH_MOVIE = "search/movie"
    const val POPULAR_MOVIES = "movie/popular"
    const val NOW_PLAYING_MOVIES = "movie/now_playing"
}

object HttpParams {
    const val TIME_OUT = 15000L
    const val API_KEY = "api_key"
    const val QUERY = "query"
    const val PAGE = "page"
}