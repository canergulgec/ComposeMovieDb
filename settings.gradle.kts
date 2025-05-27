pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ComposeMovieDb"

include(":app")
include(":feature:search")
include(":feature:detail")
include(":feature:home")
include(":core:data")
include(":core:ui")
include(":core:common")
include(":core:domain")
include(":core:model")
include(":core:testing")
