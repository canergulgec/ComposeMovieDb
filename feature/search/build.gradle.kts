plugins {
    id(ConfigData.ANDROID_LIBRARY)
    id(ConfigData.KOTLIN_ANDROID)
    id(ConfigData.KOTLIN_KAPT)
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
    implementation(project(Modules.CORE_COMMON))
    implementation(project(Modules.CORE_UI))
    implementation(project(Modules.CORE_DATA))
    implementation(project(Modules.CORE_DOMAIN))
    implementation(project(Modules.CORE_MODEL))

    // AndroidX
    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.APPCOMPAT)
    implementation(Dependencies.AndroidX.LIFECYCLE_VIEWMODEL)

    // Dagger Hilt
    implementation(Dependencies.Dagger.DAGGER_HILT)
    kapt(Dependencies.Dagger.HILT_COMPILER)
    implementation(Dependencies.Dagger.HILT_NAVIGATION)

    // Compose
    implementation(platform(Dependencies.Compose.COMPOSE_BOM))
    implementation(Dependencies.Compose.COMPOSE_COMPILER)
    implementation(Dependencies.Compose.COMPOSE_UI)
    implementation(Dependencies.Compose.COMPOSE_MATERIAL3)
    implementation(Dependencies.Compose.COMPOSE_LIFECYCLE)
    implementation(Dependencies.Compose.COMPOSE_UI_TOOLING_PREVIEW)
    debugImplementation(Dependencies.Compose.COMPOSE_UI_TOOLING)

    // Image Loading
    implementation(Dependencies.ImageLoader.COIL)

    // Timber
    implementation(Dependencies.Logger.TIMBER)

    testImplementation(Dependencies.Test.JUNIT)
    androidTestImplementation(Dependencies.Test.JUNIT_EXT)
    androidTestImplementation(Dependencies.Test.ESPRESSO)
}