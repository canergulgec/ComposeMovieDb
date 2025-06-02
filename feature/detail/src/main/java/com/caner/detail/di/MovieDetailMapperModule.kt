package com.caner.detail.di

import com.caner.domain.mapper.Mapper
import com.caner.model.MovieDetailModel
import com.caner.model.remote.MovieDetailResponse
import com.caner.domain.mapper.MovieDetailMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieDetailMapperModule {

    @Binds
    abstract fun bindsMovieDetailMapper(mapper: MovieDetailMapper): Mapper<MovieDetailResponse, MovieDetailModel>
}
