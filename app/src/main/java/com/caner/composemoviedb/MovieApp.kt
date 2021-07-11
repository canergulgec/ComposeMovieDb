package com.caner.composemoviedb

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.caner.composemoviedb.common.Constants
import com.caner.composemoviedb.utils.SharedPrefUtils
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MovieApp : Application() {

    @Inject
    lateinit var sharedPrefs: SharedPrefUtils

    val isDark = mutableStateOf(false)

    override fun onCreate() {
        super.onCreate()
        setAppTheme()
    }

    private fun setAppTheme() {
        val currentTheme = sharedPrefs.getData(Constants.NIGHT_MODE, false)
        isDark.value = currentTheme
    }

    fun changeTheme() {
        sharedPrefs.putData(Constants.NIGHT_MODE, !isDark.value)
        isDark.value = !isDark.value
    }
}