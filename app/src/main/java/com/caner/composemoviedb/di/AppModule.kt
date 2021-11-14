package com.caner.composemoviedb.di

import android.content.Context
import com.caner.composemoviedb.data.local.ThemeManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun providePreferenceManager(@ApplicationContext context: Context): ThemeManagerImpl {
        return ThemeManagerImpl(context = context)
    }
}