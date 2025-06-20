object Dependencies {

    object AndroidX {
        const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.AndroidX.APP_COMPAT}"
        const val CORE_KTX = "androidx.core:core-ktx:${Versions.AndroidX.CORE_KTX}"
        const val SPLASH_SCREEN = "androidx.core:core-splashscreen:${Versions.AndroidX.SPLASH}"
        const val DATASTORE = "androidx.datastore:datastore-preferences:${Versions.AndroidX.DATASTORE}"
        const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${Versions.AndroidX.ACTIVITY_COMPOSE}"
        const val LIFECYCLE_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.LIFECYCLE_KTX}"
    }

    object Dagger {
        const val DAGGER_HILT = "com.google.dagger:hilt-android:${Versions.Dagger.HILT}"
        const val HILT_COMPILER = "com.google.dagger:hilt-compiler:${Versions.Dagger.HILT}"
        const val HILT_NAVIGATION = "androidx.hilt:hilt-navigation-compose:${Versions.Dagger.NAVIGATION}"
    }

    object Compose {
        const val COMPOSE_BOM = "androidx.compose:compose-bom:${Versions.Compose.BOM}"
        const val COMPOSE_MATERIAL3 = "androidx.compose.material3:material3"
        const val COMPOSE_UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview"
        const val COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling"
        const val COMPOSE_NAVIGATION = "androidx.navigation:navigation-compose:${Versions.Compose.NAVIGATION}"
    }

    object Google {
        const val MATERIAL = "com.google.android.material:material:${Versions.Google.MATERIAL}"
    }

    object Network {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.Network.RETROFIT}"
        const val RETROFIT_MOSHI = "com.squareup.retrofit2:converter-moshi:${Versions.Network.RETROFIT}"
        const val MOSHI_KOTLIN = "com.squareup.moshi:moshi-kotlin:${Versions.Network.MOSHI}"
        const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.Network.OKHTTP}"
        const val OKHTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor:${Versions.Network.OKHTTP}"
    }

    object Firebase {
        const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${Versions.Firebase.BOM}"
        const val FIREBASE_CRASHLYTICS = "com.google.firebase:firebase-crashlytics-ktx"
        const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics-ktx"
    }

    object Logger {
        const val TIMBER = "com.jakewharton.timber:timber:${Versions.Logger.TIMBER}"
    }

    object ImageLoader {
        const val COIL = "io.coil-kt:coil-compose:${Versions.ImageLoader.COIL}"
    }

    object Animation {
        const val LOTTIE_ANIMATION = "com.airbnb.android:lottie-compose:${Versions.Animation.LOTTIE}"
    }

    object Test {
        const val JUNIT = "junit:junit:${Versions.Test.JUNIT_4}"
        const val JUNIT_EXT = "androidx.test.ext:junit:${Versions.Test.JUNIT_EXT}"
        const val MOCKK = "io.mockk:mockk:${Versions.Test.MOCKK}"
        const val TRUTH = "com.google.truth:truth:${Versions.Test.TRUTH}"
        const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Test.COROUTINES}"
        const val CORE_TESTING = "androidx.arch.core:core-testing:${Versions.Test.CORE_TESTING}"
        const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.Test.ESPRESSO}"
        const val TURBINE = "app.cash.turbine:turbine:${Versions.Test.TURBINE}"
    }
}