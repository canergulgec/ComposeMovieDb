import java.util.Properties
import java.io.FileInputStream

plugins {
    id(ConfigData.ANDROID_LIBRARY)
    id(ConfigData.KOTLIN_ANDROID)
    id(ConfigData.KSP)
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
    implementation(project(Modules.CORE_DOMAIN))

    // AndroidX
    implementation(Dependencies.AndroidX.DATASTORE)

    // Network
    implementation(Dependencies.Network.RETROFIT)
    implementation(Dependencies.Network.RETROFIT_MOSHI)
    implementation(Dependencies.Network.MOSHI_KOTLIN)
    implementation(Dependencies.Network.OKHTTP)
    implementation(Dependencies.Network.OKHTTP_LOGGING)

    // Dagger
    implementation(Dependencies.Dagger.DAGGER_HILT)
    ksp(Dependencies.Dagger.HILT_COMPILER)

    // Logger
    implementation(Dependencies.Logger.TIMBER)
}

fun getApiKey(): String {
    val prop = Properties().apply {
        load(FileInputStream(File(rootProject.rootDir, "./local.properties")))
    }
    return prop.getProperty("MOVIE_API_KEY")
}