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

    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Compose.COMPILER
    }

    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    implementation(platform(Dependencies.Compose.COMPOSE_BOM))
    implementation(Dependencies.Compose.COMPOSE_UI)
    implementation(Dependencies.Compose.COMPOSE_MATERIAL3)
    implementation(Dependencies.Network.KTOR_GSON)

    testImplementation(Dependencies.Test.JUNIT)
    androidTestImplementation(Dependencies.Test.JUNIT_EXT)
    androidTestImplementation(Dependencies.Test.ESPRESSO)
}