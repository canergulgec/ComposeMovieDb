plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
}

android {
    namespace = Namespaces.APP_PACKAGE
    compileSdk = Versions.App.COMPILER_SDK

    defaultConfig {
        applicationId = ConfigData.APPLICATION_ID
        minSdk = Versions.App.MIN_SDK
        targetSdk = Versions.App.TARGET_SDK
        versionCode = Versions.App.VERSION_CODE
        versionName = Versions.App.VERSION_NAME
        testInstrumentationRunner = ConfigData.TEST_RUNNER
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Compose.COMPILER
    }
    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    implementation(project(Modules.FEATURE_HOME))
    implementation(project(Modules.FEATURE_DETAIL))
    implementation(project(Modules.FEATURE_SEARCH))
    implementation(project(Modules.CORE_COMMON))
    implementation(project(Modules.CORE_UI))
    implementation(project(Modules.CORE_DATA))

    // AndroidX
    implementation(libs.androidx.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.splash.screen)

    // Dagger Hilt
    implementation(libs.dagger.hilt)
    ksp(libs.dagger.hilt.compiler)
    implementation(libs.dagger.hilt.navigation)

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material3)
    implementation(libs.compose.navigation)

    // Google
    implementation(libs.google.material)

    // Logger
    implementation(libs.timber)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)

    // Test
    testImplementation(libs.junit4)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso)
}
