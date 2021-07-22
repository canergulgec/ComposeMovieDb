package com.caner.composemoviedb.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.caner.composemoviedb.data.Movie
import com.caner.composemoviedb.data.mapper.MovieMapper
import com.caner.composemoviedb.common.Constants
import com.caner.composemoviedb.domain.api.MovieApi
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(
    private val movieApi: MovieApi,
    private val movieMapper: MovieMapper,
    private val type: Int
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1

        return try {
            val apiRequest = if (type == Constants.NOW_PLAYING_MOVIES) {
                movieApi.getNowPlayingMovies(getParams(page))
            } else {
                movieApi.getUpcomingMovies(getParams(page))
            }
            apiRequest.run {
                val data = movieMapper.to(this)
                LoadResult.Page(
                    data = data.movies,
                    prevKey = null,
                    nextKey = if (page == this.total) null else page + 1
                )
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    private fun getParams(page: Int): HashMap<String, Any> {
        return object : LinkedHashMap<String, Any>() {
            init {
                put(Constants.PAGE, page)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }
}