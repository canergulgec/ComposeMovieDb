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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(Modules.navigation))
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

    // Google
    implementation(Dependencies.Google.material)

    // Dagger Hilt
    implementation(Dependencies.Dagger.daggerHilt)
    kapt(Dependencies.Dagger.daggerHiltCompiler)
    implementation(Dependencies.Dagger.daggerNavigation)

    // Compose
    implementation(Dependencies.Compose.composeCompiler)
    implementation(Dependencies.Compose.composeUi)
    implementation(Dependencies.Compose.composeMaterial)
    implementation(Dependencies.Compose.composeLifecycle)
    implementation(Dependencies.Compose.composeNavigation)

    // Accompanist
    implementation(Dependencies.Accompanist.controller)
    implementation(Dependencies.Accompanist.pager)

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
