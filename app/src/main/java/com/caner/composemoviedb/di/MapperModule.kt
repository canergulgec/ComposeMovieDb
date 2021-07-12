package com.caner.composemoviedb.di

import com.caner.composemoviedb.data.MovieModel
import com.caner.composemoviedb.data.remote.MoviesResponse
import com.caner.composemoviedb.data.mapper.MovieMapper
import com.caner.composemoviedb.common.Mapper
import com.caner.composemoviedb.data.MovieDetailModel
import com.caner.composemoviedb.data.mapper.MovieDetailMapper
import com.caner.composemoviedb.data.remote.MovieDetailResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun bindsMovieMapper(mapper: MovieMapper): Mapper<MoviesResponse, MovieModel>

    @Binds
    abstract fun bindsMovieDetailMapper(mapper: MovieDetailMapper): Mapper<MovieDetailResponse, MovieDetailModel>
}