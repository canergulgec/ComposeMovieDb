plugins {
    id(ConfigData.ANDROID_APPLICATION) version Versions.ClassPath.GRADLE apply false
    id(ConfigData.KOTLIN_ANDROID) version Versions.ClassPath.KOTLIN apply false
    id(ConfigData.HILT_ANDROID) version Versions.Dagger.HILT apply false
    id(ConfigData.KSP) version Versions.ClassPath.KSP apply false
    id(ConfigData.GOOGLE_SERVICES) version Versions.ClassPath.GOOGLE_SERVICES apply false
    id(ConfigData.FIREBASE_CRASHLYTICS) version Versions.ClassPath.CRASHLYTICS apply false
}