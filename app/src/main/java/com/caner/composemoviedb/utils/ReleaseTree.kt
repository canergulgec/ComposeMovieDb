package com.caner.composemoviedb.utils

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

const val CRASHLYTICS_TAG = "CRASHLYTICS_TAG"

class ReleaseTree : Timber.Tree() {

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return priority == Log.ERROR || priority == Log.INFO
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        when (priority) {
            Log.ERROR -> t?.let { e ->
                FirebaseCrashlytics.getInstance().recordException(e)
            }
            Log.INFO -> tag?.takeIf { it == CRASHLYTICS_TAG }?.let {
                FirebaseCrashlytics.getInstance().log(message)
            }
            else -> Unit
        }
    }
}