plugins {
    id(ConfigData.androidApplication)
    id(ConfigData.kotlinAndroid)
    id(ConfigData.kotlinKapt)
    id(ConfigData.daggerHilt)
    id(ConfigData.kotlinParcelize)
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = Namespaces.app
    compileSdk = Versions.App.compileSdkVersion

    defaultConfig {
        applicationId = ConfigData.applicationID
        minSdk = Versions.App.minSdkVersion
        targetSdk = Versions.App.targetSdkVersion
        versionCode = Versions.App.versionCode
        versionName = Versions.App.versionName
        testInstrumentationRunner = ConfigData.testRunner
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
        kotlinCompilerExtensionVersion = Versions.Compose.compilerVersion
    }
    kotlin {
        jvmToolchain(17)
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(Modules.feature_home))
    implementation(project(Modules.feature_detail))
    implementation(project(Modules.feature_search))
    implementation(project(Modules.core_common))
    implementation(project(Modules.core_ui))
    implementation(project(Modules.core_data))

    // AndroidX
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.splashScreen)
    implementation(Dependencies.AndroidX.activityCompose)

    // Google
    implementation(Dependencies.Google.material)

    // Dagger Hilt
    implementation(Dependencies.Dagger.daggerHilt)
    kapt(Dependencies.Dagger.daggerHiltCompiler)
    implementation(Dependencies.Dagger.daggerNavigation)

    // Compose
    implementation(platform(Dependencies.Compose.composeBom))
    implementation(Dependencies.Compose.composeCompiler)
    implementation(Dependencies.Compose.composeMaterial3)
    implementation(Dependencies.Compose.composeNavigation)

    // Timber
    implementation(Dependencies.Logger.timber)

    // Firebase
    implementation(platform(Dependencies.Firebase.firebaseBom))
    implementation(Dependencies.Firebase.crashlytics)
    implementation(Dependencies.Firebase.analytics)

    // Testing
    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.jUnitExt)
    androidTestImplementation(Dependencies.Test.espresso)
}
