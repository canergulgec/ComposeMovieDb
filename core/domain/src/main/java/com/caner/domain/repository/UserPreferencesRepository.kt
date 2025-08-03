package com.caner.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    fun isDarkTheme(): Flow<Boolean>
    suspend fun setDarkTheme(isDark: Boolean)
} 