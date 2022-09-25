plugins {
    id(ConfigData.androidLibrary)
    id(ConfigData.kotlinAndroid)
}

android {
    compileSdk = Versions.App.compileSdkVersion

    defaultConfig {
        minSdk = Versions.App.minSdkVersion
        targetSdk = Versions.App.targetSdkVersion
        testInstrumentationRunner = ConfigData.testRunner
    }
}

dependencies {
    // AndroidX
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.splashScreen)

    // Google
    implementation(Dependencies.Google.material)

    // Coroutines
    implementation(Dependencies.Coroutines.coroutinesAndroid)
}