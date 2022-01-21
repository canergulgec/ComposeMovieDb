package com.caner.composemoviedb.domain.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.caner.composemoviedb.data.model.Movie
import com.caner.composemoviedb.data.mapper.MovieMapper
import com.caner.composemoviedb.utils.Constants
import com.caner.composemoviedb.data.model.remote.MoviesResponse
import com.caner.composemoviedb.utils.network.HttpParams
import com.caner.composemoviedb.utils.network.HttpRoutes
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(
    private val client: HttpClient,
    private val mapper: MovieMapper
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: Constants.MOVIE_STARTING_PAGE_INDEX

        return try {
            val response: MoviesResponse = client.get {
                url(HttpRoutes.NOW_PLAYING_MOVIES)
                parameter(HttpParams.PAGE, page)
            }
            response.run {
                val data = mapper.to(this)
                LoadResult.Page(
                    data = data.movies,
                    prevKey = if (page == Constants.MOVIE_STARTING_PAGE_INDEX) null else page - 1,
                    nextKey = if (page == this.total) null else page + 1
                )
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}