plugins {
    id(ConfigData.androidLibrary)
    id(ConfigData.kotlinAndroid)
    id(ConfigData.kotlinKapt)
    id(ConfigData.daggerHilt)
}

android {
    namespace = Namespaces.feature_detail
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
        kotlinCompilerExtensionVersion = "1.2.0" // Change this later
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
    implementation(Dependencies.Compose.composeUi)
    implementation(Dependencies.Compose.composeMaterial)
    implementation(Dependencies.Compose.composeLifecycle)
    implementation(Dependencies.Compose.composeTooling)

    // Image Loading
    implementation(Dependencies.ImageLoader.coil)

    // Accompanist
    implementation(Dependencies.Accompanist.flowLayout)

    // Timber
    implementation(Dependencies.Logger.timber)

    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.jUnitExt)
    androidTestImplementation(Dependencies.Test.espresso)
}