plugins {
    id(ConfigData.ANDROID_LIBRARY)
    id(ConfigData.KOTLIN_ANDROID)
    id(ConfigData.KSP)
    id(ConfigData.HILT_ANDROID)
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
    implementation(Dependencies.Dagger.DAGGER_HILT)
    ksp(Dependencies.Dagger.HILT_COMPILER)

    testImplementation(Dependencies.Test.JUNIT)
}