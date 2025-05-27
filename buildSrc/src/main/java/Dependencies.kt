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
        const val KTOR_CLIENT = "io.ktor:ktor-client-android:${Versions.Network.KTOR}"
        const val KTOR_CORE = "io.ktor:ktor-client-core:${Versions.Network.KTOR}"
        const val KTOR_GSON = "io.ktor:ktor-client-gson:${Versions.Network.KTOR}"
        const val KTOR_LOGGING = "io.ktor:ktor-client-logging-jvm:${Versions.Network.KTOR}"
        const val KTOR_OKHTTP = "io.ktor:ktor-client-okhttp:${Versions.Network.KTOR}"
    }

    object Firebase {
        const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${Versions.Firebase.BOM}"
        const val FIREBASE_CRASHLYTICS = "com.google.firebase:firebase-crashlytics-ktx"
        const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics-ktx"
    }

    object Coroutines {
        const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Coroutines.COROUTINES}"
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
        const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Test.COROUTINES}"
        const val CORE_TESTING = "androidx.arch.core:core-testing:${Versions.Test.CORE_TESTING}"
        const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.Test.ESPRESSO}"
    }
}