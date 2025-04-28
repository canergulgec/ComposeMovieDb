plugins {
    id(ConfigData.ANDROID_LIBRARY)
    id(ConfigData.KOTLIN_ANDROID)
}

android {
    namespace = Namespaces.CORE_COMMON_PACKAGE
    compileSdk = Versions.App.COMPILER_SDK

    defaultConfig {
        minSdk = Versions.App.MIN_SDK
        testInstrumentationRunner = ConfigData.TEST_RUNNER
    }

    // Add JVM Toolchain for consistency
    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    // AndroidX
    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.APPCOMPAT)
    implementation(Dependencies.AndroidX.SPLASH_SCREEN)

    // Google
    implementation(Dependencies.Google.MATERIAL)

    // Coroutines
    implementation(Dependencies.Coroutines.COROUTINES)
}