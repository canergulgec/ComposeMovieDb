package com.caner.composemoviedb.di

import com.caner.composemoviedb.domain.api.MovieApi
import com.caner.composemoviedb.domain.api.MovieDetailApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Provides
    fun provideMovieApiService(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    fun provideMovieDetailApiService(retrofit: Retrofit): MovieDetailApi {
        return retrofit.create(MovieDetailApi::class.java)
    }
}