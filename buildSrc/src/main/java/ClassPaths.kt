object ClassPaths {
    const val gradle = "com.android.tools.build:gradle:${Versions.ClassPath.gradleVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.ClassPath.kotlinVersion}"
    const val daggerHiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.Dagger.daggerHiltVersion}"
    const val googleServices = "com.google.gms:google-services:${Versions.ClassPath.googleServices}"
    const val crashlytics = "com.google.firebase:firebase-crashlytics-gradle:${Versions.ClassPath.crashlytics}"
}
