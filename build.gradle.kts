// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(ClassPaths.GRADLE_PATH)
        classpath(ClassPaths.KOTLIN_PATH)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
        classpath(ClassPaths.HILT_PATH)
        classpath(ClassPaths.GOOGLE_SERVICES_PATH)
        classpath(ClassPaths.CRASHLYTICS_PATH)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}