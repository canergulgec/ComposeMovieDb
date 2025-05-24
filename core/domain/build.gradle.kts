plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id(ConfigData.KOTLIN_KAPT) //TODO: Replace it with ksp
    alias(libs.plugins.hilt)
}

android {
    namespace = Namespaces.CORE_DOMAIN_PACKAGE
    compileSdk = Versions.App.COMPILER_SDK

    defaultConfig {
        minSdk = Versions.App.MIN_SDK
        testInstrumentationRunner = ConfigData.TEST_RUNNER
    }

    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    implementation(project(Modules.CORE_COMMON))
    implementation(project(Modules.CORE_DATA))
    implementation(project(Modules.CORE_MODEL))

    // Dagger Hilt
    implementation(libs.dagger.hilt)
    kapt(libs.dagger.hilt.compiler)

    // Network
    implementation(libs.ktor.client.gson)

    // Test
    testImplementation(libs.junit4)
}