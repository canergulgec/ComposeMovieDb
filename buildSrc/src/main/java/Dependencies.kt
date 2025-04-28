object Dependencies {

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompatVersion}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.AndroidX.coreKtxVersion}"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.lifecycleVersion}"
        const val splashScreen = "androidx.core:core-splashscreen:${Versions.AndroidX.splashScreenVersion}"
        const val dataStore = "androidx.datastore:datastore-preferences:${Versions.AndroidX.dataStoreVersion}"
        const val activityCompose = "androidx.activity:activity-compose:${Versions.AndroidX.activityComposeVersion}"
    }

    object Google {
        const val material = "com.google.android.material:material:${Versions.Google.materialVersion}"
    }

    object Dagger {
        const val daggerHilt = "com.google.dagger:hilt-android:${Versions.Dagger.daggerHiltVersion}"
        const val daggerHiltCompiler = "com.google.dagger:hilt-compiler:${Versions.Dagger.daggerHiltVersion}"
        const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:${Versions.Dagger.navigation}"
    }

    object Compose {
        const val composeBom = "androidx.compose:compose-bom:2025.04.01"
        const val composeCompiler = "androidx.compose.compiler:compiler:${Versions.Compose.compilerVersion}"
        const val composeUi = "androidx.compose.ui:ui"
        const val composeUtil = "androidx.compose.ui:ui-util"
        const val composeMaterial3 = "androidx.compose.material3:material3"
        const val composeLifecycle = "androidx.lifecycle:lifecycle-runtime-compose"
        const val composeTooling = "androidx.compose.ui:ui-tooling-preview"
        const val composeFoundation = "androidx.compose.foundation:foundation"
        const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.Compose.composeNavigation}"
        const val composePaging = "androidx.paging:paging-compose:${Versions.Compose.pagingVersion}"
    }

    object Network {
        const val ktorAndroid = "io.ktor:ktor-client-android:${Versions.Network.ktorVersion}"
        const val ktorCore = "io.ktor:ktor-client-core:${Versions.Network.ktorVersion}"
        const val ktorGson = "io.ktor:ktor-client-gson:${Versions.Network.ktorVersion}"
        const val ktorLogging = "io.ktor:ktor-client-logging-jvm:${Versions.Network.ktorVersion}"
        const val ktorOkhttp = "io.ktor:ktor-client-okhttp:${Versions.Network.ktorVersion}"
    }

    object Firebase {
        const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.Firebase.firebaseVersion}"
        const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
        const val analytics = "com.google.firebase:firebase-analytics-ktx"
    }

    object Coroutines {
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Coroutines.coroutinesVersion}"
    }

    object Logger {
        const val timber = "com.jakewharton.timber:timber:${Versions.Logger.timberVersion}"
    }

    object ImageLoader {
        const val coil = "io.coil-kt:coil-compose:${Versions.ImageLoader.coilVersion}"
    }

    object Animation {
        const val lottie = "com.airbnb.android:lottie-compose:${Versions.Animation.lottieVersion}"
    }

    object Test {
        const val jUnit = "junit:junit:${Versions.Test.junit4Version}"
        const val jUnitExt = "androidx.test.ext:junit:${Versions.Test.junitExt}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.Test.espressoVersion}"
    }
}