plugins {
    id(ConfigData.ANDROID_LIBRARY)
    id(ConfigData.KOTLIN_ANDROID)
}

android {
    namespace = Namespaces.CORE_MODEL_PACKAGE
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
    implementation(Dependencies.Network.MOSHI_KOTLIN)

    testImplementation(Dependencies.Test.JUNIT)
    androidTestImplementation(Dependencies.Test.JUNIT_EXT)
    androidTestImplementation(Dependencies.Test.ESPRESSO)
}