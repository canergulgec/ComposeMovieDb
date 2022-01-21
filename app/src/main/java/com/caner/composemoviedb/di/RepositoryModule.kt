package com.caner.composemoviedb.di

import com.caner.composemoviedb.data.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideMoviesRepositoryImp(repositoryImp: MovieRepositoryImp): MovieRepository

    @Binds
    abstract fun provideMovieDetailRepositoryImp(repositoryImp: MovieDetailRepositoryImp): MovieDetailRepository

    @Binds
    abstract fun provideSearchRepositoryImp(repositoryImp: SearchRepositoryImp): SearchRepository
}