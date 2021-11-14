package com.caner.composemoviedb.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.caner.composemoviedb.data.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.themePrefDataStore by preferencesDataStore(Constants.PREF_KEY)

class ThemeManagerImpl(context: Context) {

    private val dataStore = context.themePrefDataStore

    val uiModeFlow: Flow<Boolean> = dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preference -> preference[IS_DARK_MODE] ?: false }

    suspend fun setDarkMode(enable: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_DARK_MODE] = enable
        }
    }

    companion object {
        val IS_DARK_MODE = booleanPreferencesKey(Constants.DARK_MODE)
    }
}