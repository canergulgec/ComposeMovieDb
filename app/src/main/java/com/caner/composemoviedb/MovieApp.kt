package com.caner.composemoviedb

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.caner.composemoviedb.common.Constants
import com.caner.composemoviedb.utils.SharedPrefUtils
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
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
        initFlipper()
    }

    private fun initFlipper() {
        SoLoader.init(this, false)
        val client = AndroidFlipperClient.getInstance(this)
        client.apply {
            addPlugin(NetworkFlipperPlugin())
            addPlugin(InspectorFlipperPlugin(this@MovieApp, DescriptorMapping.withDefaults()))
            start()
        }
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