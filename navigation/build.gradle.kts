plugins {
    id(ConfigData.androidLibrary)
    id(ConfigData.kotlinAndroid)
}

android {
    namespace = Namespaces.navigation
    compileSdk = Versions.App.compileSdkVersion

    defaultConfig {
        minSdk = Versions.App.minSdkVersion
        testInstrumentationRunner = ConfigData.testRunner
    }
}

dependencies {
    implementation(project(Modules.core_common))

    // Compose Navigation
    implementation(Dependencies.Compose.composeNavigation)
}