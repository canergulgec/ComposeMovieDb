plugins {
    id(ConfigData.androidLibrary)
    id(ConfigData.kotlinAndroid)
    id(ConfigData.kotlinKapt)
    id(ConfigData.daggerHilt)
}

android {
    namespace = Namespaces.feature_home
    compileSdk = Versions.App.compileSdkVersion

    defaultConfig {
        minSdk = Versions.App.minSdkVersion
        testInstrumentationRunner = ConfigData.testRunner
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

dependencies {
    implementation(project(Modules.core_common))
    implementation(project(Modules.core_ui))
    implementation(project(Modules.core_data))
    implementation(project(Modules.core_domain))

    // AndroidX
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.lifecycleViewModel)

    // Dagger Hilt
    implementation(Dependencies.Dagger.daggerHilt)
    kapt(Dependencies.Dagger.daggerHiltCompiler)

    // Compose
    implementation(Dependencies.Compose.composeCompiler)
    implementation(Dependencies.Compose.composeUtil)
    implementation(Dependencies.Compose.composeUi)
    implementation(Dependencies.Compose.composeMaterial)
    implementation(Dependencies.Compose.composeLifecycle)
    implementation(Dependencies.Compose.composePaging)

    // Accompanist
    implementation(Dependencies.Accompanist.pager)

    // Image Loading
    implementation(Dependencies.ImageLoader.coil)

    // Timber
    implementation(Dependencies.Logger.timber)

    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.jUnitExt)
    androidTestImplementation(Dependencies.Test.espresso)
}