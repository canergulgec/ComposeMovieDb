import java.util.Properties
import java.io.FileInputStream

plugins {
    id(ConfigData.androidLibrary)
    id(ConfigData.kotlinAndroid)
    id(ConfigData.kotlinKapt)
    id(ConfigData.daggerHilt)
}

android {
    namespace = Namespaces.core_data
    compileSdk = Versions.App.compileSdkVersion

    defaultConfig {
        minSdk = Versions.App.minSdkVersion
        testInstrumentationRunner = ConfigData.testRunner

        buildConfigField("Integer", "TIMEOUT", "60")
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "API_KEY", getApiKey())
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
    implementation(project(Modules.core_domain))

    // DataStore
    implementation(Dependencies.AndroidX.dataStore)

    // Dagger Hilt
    implementation(Dependencies.Dagger.daggerHilt)
    kapt(Dependencies.Dagger.daggerHiltCompiler)

    // Compose
    implementation(Dependencies.Compose.composePaging)
    implementation(Dependencies.Compose.composeTooling)

    // Ktor
    implementation(Dependencies.Network.ktorAndroid)
    implementation(Dependencies.Network.ktorCore)
    implementation(Dependencies.Network.ktorGson)
    implementation(Dependencies.Network.ktorLogging)
    implementation(Dependencies.Network.ktorOkhttp)

    // Timber
    implementation(Dependencies.Logger.timber)

    testImplementation(Dependencies.Test.jUnit)
}

fun getApiKey(): String {
    val prop = Properties().apply {
        load(FileInputStream(File(rootProject.rootDir, "./local.properties")))
    }
    return prop.getProperty("MOVIE_API_KEY")
}