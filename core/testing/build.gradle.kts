plugins {
    id(ConfigData.ANDROID_LIBRARY)
    id(ConfigData.KOTLIN_ANDROID)
}

android {
    namespace = Namespaces.CORE_TESTING
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
    // Core modules
    implementation(project(Modules.CORE_COMMON))
    implementation(project(Modules.CORE_DOMAIN))
    implementation(project(Modules.CORE_MODEL))
    implementation(project(Modules.CORE_DATA))
    implementation(project(Modules.FEATURE_HOME))
    implementation(project(Modules.FEATURE_SEARCH))

    // AndroidX
    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.LIFECYCLE_KTX)

    // Testing
    api(Dependencies.Test.JUNIT)
    api(Dependencies.Test.COROUTINES)
    testImplementation(Dependencies.Test.MOCKK)
    testImplementation(Dependencies.Test.TRUTH)
    testImplementation(Dependencies.Test.CORE_TESTING)
    testImplementation(Dependencies.Test.TURBINE)
} 