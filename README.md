# ComposeMovieDb

### 💫 &nbsp; Powered by [TheMovieDb](https://www.themoviedb.org) built with Jetpack Compose.

![alt text](https://cdn-images-1.medium.com/max/1200/1*vIR7iO-1GnY2xYxL6NiYkw.png)

## ⚙️ Configuration

In order to use MovieDB:
- You need to get API KEY from TMDb. You can do that by clicking [here](https://www.themoviedb.org/signup).
- Once you obtain key, add your key into local.properties `MOVIE_API_KEY = "xxx" `
- Use **JDK 17** to build this project.

## 🌞 Light Mode

<img src="/art/movie_screen_light.png" width="250" /> &nbsp;&nbsp;&nbsp; <img src="/art/movie_detail_light.png" width="250" /> &nbsp;&nbsp;&nbsp; <img src="/art/search_list_light.png" width="250" />

## 🌚 Dark Mode

<img src="/art/movie_screen_dark.png" width="250" /> &nbsp;&nbsp;&nbsp; <img src="/art/movie_detail_dark.png" width="250" /> &nbsp;&nbsp;&nbsp; <img src="/art/search_list_dark.png" width="250" />

## 🛠 Built With

- [Kotlin](https://kotlinlang.org)
- [Retrofit](https://square.github.io/retrofit/) + [Moshi](https://github.com/square/moshi) for networking
- [Dagger Hilt](https://dagger.dev/hilt/) for dependency injection
- [Coroutines Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) for reactive programming
- [Splash Screen API](https://developer.android.com/guide/topics/ui/splash-screen/migrate)
- [Jetpack](https://developer.android.com/jetpack)
    * [Compose](https://developer.android.com/jetpack/compose) with [Material3](https://developer.android.com/jetpack/compose/designsystems/material3)
    * [Navigation Compose](https://developer.android.com/jetpack/compose/navigation)
    * [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) for preferences
    * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) & [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [Firebase](https://firebase.google.com/)
    * [Crashlytics](https://firebase.google.com/products/crashlytics) for crash reporting
    * [Analytics](https://firebase.google.com/products/analytics) for user analytics
- [Coil](https://coil-kt.github.io/coil/) for image loading
- [Lottie](https://airbnb.design/lottie/) for animations
- [Timber](https://github.com/JakeWharton/timber) for logging

## 🏗 Architecture

This project follows **Clean Architecture** principles with a **modular approach**:

### 📁 Module Structure
- **App Module**: Main application module with navigation setup
- **Core Modules**:
  - `core:common` - Shared utilities, constants, and extensions
  - `core:data` - Data layer with repositories, API, and local storage
  - `core:domain` - Business logic with use cases and repository interfaces
  - `core:ui` - Shared UI components and theming
  - `core:testing` - Testing utilities and shared test data
- **Feature Modules**:
  - `feature:home` - Home screen with popular movies
  - `feature:detail` - Movie detail screen
  - `feature:search` - Movie search functionality

### 🔄 Architecture Pattern
- **MVVM (Model-View-ViewModel)** pattern for presentation layer
- **Repository Pattern** for data access abstraction
- **Use Cases** for business logic encapsulation
- **Dependency Injection** with Dagger Hilt
- **Unidirectional Data Flow** with StateFlow/Flow

## 🧪 Testing

Comprehensive testing setup with:
- **Unit Tests**: JUnit 4, MockK, Truth assertions
- **Coroutine Testing**: kotlinx-coroutines-test with Turbine for Flow testing
- **Architecture Testing**: androidx.arch.core.core-testing
- **Test Coverage**: ViewModels, Use Cases, and Repository implementations

Testing libraries used:
- [JUnit 4](https://junit.org/junit4/) for test framework
- [MockK](https://mockk.io/) for mocking
- [Truth](https://truth.dev/) for fluent assertions
- [Turbine](https://github.com/cashapp/turbine) for testing Flow emissions
- [Coroutines Test](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-test/) for testing suspending functions

## 📱 Features

- **Popular Movies**: Browse trending and popular movies
- **Movie Details**: Detailed view with ratings, overview, and additional information
- **Search**: Search movies by title with real-time suggestions
- **Dark/Light Theme**: Adaptive theming support
- **Modular Architecture**: Clean, scalable, and maintainable codebase
- **Offline Support**: DataStore for local preferences
- **Error Handling**: Comprehensive error handling with user-friendly messages
- **Crash Reporting**: Firebase Crashlytics integration

## 🎯 Tech Specifications

- **Min SDK**: 26 (Android 8.0)
- **Target SDK**: 35
- **Compile SDK**: 35
- **Java Version**: 17
- **Kotlin Version**: 1.9.22
- **Compose BOM**: 2025.04.01
