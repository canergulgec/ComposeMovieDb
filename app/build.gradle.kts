plugins {
    id(ConfigData.ANDROID_APPLICATION)
    id(ConfigData.KOTLIN_ANDROID)
    id(ConfigData.KOTLIN_KAPT)
    id(ConfigData.HILT_ANDROID)
    id(ConfigData.KOTLIN_PARCELIZE)
    id(ConfigData.GOOGLE_SERVICES)
    id(ConfigData.FIREBASE_CRASHLYTICS)
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
        kotlinCompilerExtensionVersion = Versions.Compose.KOTLIN_COMPILER
    }
    kotlin {
        jvmToolchain(17)
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(Modules.FEATURE_HOME))
    implementation(project(Modules.FEATURE_DETAIL))
    implementation(project(Modules.FEATURE_SEARCH))
    implementation(project(Modules.CORE_COMMON))
    implementation(project(Modules.CORE_UI))
    implementation(project(Modules.CORE_DATA))

    // AndroidX
    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.APPCOMPAT)
    implementation(Dependencies.AndroidX.SPLASH_SCREEN)
    implementation(Dependencies.AndroidX.ACTIVITY_COMPOSE)

    // Dagger Hilt
    implementation(Dependencies.Dagger.DAGGER_HILT)
    kapt(Dependencies.Dagger.HILT_COMPILER)
    implementation(Dependencies.Dagger.HILT_NAVIGATION)

    // Compose
    implementation(platform(Dependencies.Compose.COMPOSE_BOM))
    implementation(Dependencies.Compose.COMPOSE_MATERIAL3)
    implementation(Dependencies.Compose.COMPOSE_NAVIGATION)

    // Google
    implementation(Dependencies.Google.MATERIAL)

    // Timber
    implementation(Dependencies.Logger.TIMBER)

    // Firebase
    implementation(platform(Dependencies.Firebase.FIREBASE_BOM))
    implementation(Dependencies.Firebase.FIREBASE_CRASHLYTICS)
    implementation(Dependencies.Firebase.FIREBASE_ANALYTICS)

    // Testing
    testImplementation(Dependencies.Test.JUNIT)
    androidTestImplementation(Dependencies.Test.JUNIT_EXT)
    androidTestImplementation(Dependencies.Test.ESPRESSO)
}
