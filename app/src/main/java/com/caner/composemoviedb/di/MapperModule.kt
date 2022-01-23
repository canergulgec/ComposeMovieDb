package com.caner.composemoviedb.di

import com.caner.composemoviedb.data.model.MovieModel
import com.caner.composemoviedb.data.model.remote.MoviesResponse
import com.caner.composemoviedb.domain.mapper.MovieMapper
import com.caner.composemoviedb.domain.mapper.Mapper
import com.caner.composemoviedb.data.model.MovieDetailModel
import com.caner.composemoviedb.domain.mapper.MovieDetailMapper
import com.caner.composemoviedb.data.model.remote.MovieDetailResponse
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