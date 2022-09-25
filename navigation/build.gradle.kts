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
    implementation(project(Modules.core_common))

    // Compose Navigation
    implementation(Dependencies.Compose.composeNavigation)

    testImplementation(Dependencies.Test.jUnit)
}