package com.caner.home.di

import com.caner.common.utils.Mapper
import com.caner.data.model.MovieModel
import com.caner.data.model.remote.MoviesResponse
import com.caner.domain.mapper.MovieMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieMapperModule {

    @Binds
    abstract fun bindsMovieMapper(mapper: MovieMapper): Mapper<MoviesResponse, MovieModel>
}