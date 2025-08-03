package com.caner.data.repository.impl

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.caner.common.Constants
import com.caner.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private val Context.userPreferencesDataStore by preferencesDataStore(Constants.PREF_KEY)

class UserPreferencesRepositoryImpl @Inject constructor(
    context: Context
) : UserPreferencesRepository {

    private val dataStore = context.userPreferencesDataStore

    override fun isDarkTheme(): Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferencesKeys.IS_DARK_THEME] ?: false
        }

    override suspend fun setDarkTheme(isDark: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_DARK_THEME] = isDark
        }
    }

    private object PreferencesKeys {
        val IS_DARK_THEME = booleanPreferencesKey(Constants.DARK_MODE)
    }
} 