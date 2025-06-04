package com.caner.domain.di

import com.caner.domain.mapper.Mapper
import com.caner.domain.mapper.MovieDetailMapper
import com.caner.domain.mapper.MovieMapper
import com.caner.model.MovieDetailModel
import com.caner.model.MovieList
import com.caner.model.remote.MovieDetailResponse
import com.caner.model.remote.MovieListResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun bindsMovieMapper(mapper: MovieMapper): Mapper<MovieListResponse, MovieList>

    @Binds
    abstract fun bindsMovieDetailMapper(mapper: MovieDetailMapper): Mapper<MovieDetailResponse, MovieDetailModel>
}