plugins {
    id(ConfigData.androidLibrary)
    id(ConfigData.kotlinAndroid)
    id(ConfigData.kotlinKapt)
    id(ConfigData.daggerHilt)
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
    implementation(project(Modules.core_data))

    // Dagger Hilt
    implementation(Dependencies.Dagger.daggerHilt)
    kapt(Dependencies.Dagger.daggerHiltCompiler)

    // Compose
    implementation(Dependencies.Compose.composePaging)

    testImplementation(Dependencies.Test.jUnit)
}