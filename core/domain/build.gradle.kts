plugins {
    id(ConfigData.androidLibrary)
    id(ConfigData.kotlinAndroid)
    id(ConfigData.kotlinKapt)
    id(ConfigData.daggerHilt)
}

android {
    namespace = Namespaces.core_domain
    compileSdk = Versions.App.compileSdkVersion

    defaultConfig {
        minSdk = Versions.App.minSdkVersion
        testInstrumentationRunner = ConfigData.testRunner
    }

    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    implementation(project(Modules.core_common))

    // Dagger Hilt
    implementation(Dependencies.Dagger.daggerHilt)
    kapt(Dependencies.Dagger.daggerHiltCompiler)

    implementation(Dependencies.Network.ktorGson)

    // Compose
    implementation(Dependencies.Compose.composePaging)

    testImplementation(Dependencies.Test.jUnit)
}