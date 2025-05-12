import java.util.Properties
import java.io.FileInputStream

plugins {
    id(ConfigData.ANDROID_LIBRARY)
    id(ConfigData.KOTLIN_ANDROID)
    id(ConfigData.KOTLIN_KAPT)
    id(ConfigData.HILT_ANDROID)
}

android {
    namespace = Namespaces.CORE_DATA_PACKAGE
    compileSdk = Versions.App.COMPILER_SDK

    defaultConfig {
        minSdk = Versions.App.MIN_SDK
        testInstrumentationRunner = ConfigData.TEST_RUNNER

        buildConfigField("Integer", "TIMEOUT", "60")
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "API_KEY", getApiKey())
    }

    buildFeatures {
        buildConfig = true
    }

    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    implementation(project(Modules.CORE_COMMON))
    implementation(project(Modules.CORE_MODEL))

    // DataStore
    implementation(Dependencies.AndroidX.DATASTORE)

    // Dagger Hilt
    implementation(Dependencies.Dagger.DAGGER_HILT)
    kapt(Dependencies.Dagger.HILT_COMPILER)

    // Ktor
    implementation(Dependencies.Network.KTOR_CLIENT)
    implementation(Dependencies.Network.KTOR_CORE)
    implementation(Dependencies.Network.KTOR_GSON)
    implementation(Dependencies.Network.KTOR_LOGGING)
    implementation(Dependencies.Network.KTOR_OKHTTP)

    // Timber
    implementation(Dependencies.Logger.TIMBER)

    testImplementation(Dependencies.Test.JUNIT)
}

fun getApiKey(): String {
    val prop = Properties().apply {
        load(FileInputStream(File(rootProject.rootDir, "./local.properties")))
    }
    return prop.getProperty("MOVIE_API_KEY")
}