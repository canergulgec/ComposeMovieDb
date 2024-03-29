plugins {
    id(ConfigData.androidLibrary)
    id(ConfigData.kotlinAndroid)
}

android {
    namespace = Namespaces.core_ui
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
        kotlinCompilerExtensionVersion = "1.2.0"
    }
}

dependencies {
    implementation(project(Modules.core_common))

    // AndroidX
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appCompat)

    // Compose
    implementation(Dependencies.Compose.composeCompiler)
    implementation(Dependencies.Compose.composeUi)
    implementation(Dependencies.Compose.composeMaterial)
    implementation(Dependencies.Compose.composeTooling)

    // Lottie
    implementation(Dependencies.Animation.lottie)

    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.jUnitExt)
    androidTestImplementation(Dependencies.Test.espresso)
}