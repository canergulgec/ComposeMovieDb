package com.caner.data.di

import android.content.Context
import com.caner.data.local.ThemeManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class PreferenceManagerModule {

    @Singleton
    @Provides
    fun providePreferenceManager(@ApplicationContext context: Context): ThemeManager {
        return ThemeManager(context = context)
    }
}