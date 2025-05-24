plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
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
    implementation(libs.androidx.ktx)
    implementation(libs.androidx.appcompat)

    // Google
    implementation(libs.google.material)
}