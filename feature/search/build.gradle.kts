plugins {
    id(ConfigData.ANDROID_LIBRARY)
    id(ConfigData.KOTLIN_ANDROID)
    id(ConfigData.KSP)
    id(ConfigData.HILT_ANDROID)
}

android {
    namespace = Namespaces.FEATURE_SEARCH_PACKAGE
    compileSdk = Versions.App.COMPILER_SDK

    defaultConfig {
        minSdk = Versions.App.MIN_SDK
        testInstrumentationRunner = ConfigData.TEST_RUNNER
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Compose.KOTLIN_COMPILER
    }

    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    implementation(project(Modules.CORE_COMMON))
    implementation(project(Modules.CORE_UI))
    implementation(project(Modules.CORE_DATA))
    implementation(project(Modules.CORE_DOMAIN))
    implementation(project(Modules.CORE_MODEL))

    // AndroidX
    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.APPCOMPAT)

    // Dagger Hilt
    implementation(Dependencies.Dagger.DAGGER_HILT)
    ksp(Dependencies.Dagger.HILT_COMPILER)
    implementation(Dependencies.Dagger.HILT_NAVIGATION)

    // Compose
    implementation(platform(Dependencies.Compose.COMPOSE_BOM))
    implementation(Dependencies.Compose.COMPOSE_MATERIAL3)
    implementation(Dependencies.Compose.COMPOSE_UI_TOOLING_PREVIEW)
    debugImplementation(Dependencies.Compose.COMPOSE_UI_TOOLING)

    // Timber
    implementation(Dependencies.Logger.TIMBER)

    testImplementation(Dependencies.Test.JUNIT)
    androidTestImplementation(Dependencies.Test.JUNIT_EXT)
    androidTestImplementation(Dependencies.Test.ESPRESSO)
}