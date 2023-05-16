[![en](https://img.shields.io/badge/lang-en-red.svg)](https://github.com/lumec/MercadoLibreChallenge)

# MercadoLibreLite with Clean Architecture

This application features a simple view, with a search field located at the top of the app and the display of search results. Additionally, it provides a view with more detailed information about the selected product.

It is worth mentioning that this project implements the principles of **Clean Architecture** within the Android context. To achieve this goal, the project required modularization combined with **MVVM**, dependency injection with **Hilt**, unit testing, instrumentation testing, and UI testing. It also incorporates remote error tracking with **Timber + Firebase Crashlytics**, UI design with **Jetpack Compose**, and **SOLID** principles. All of this is built upon the versatility of Kotlin. Therefore, this small project can serve as a guide for developers who want to create decoupled, testable, maintainable, scalable apps and even take them to production.

The accomplishment of the aforementioned objectives is attributed to the knowledge acquired from the **ArchitectCoders** course by **Antonio Leiva**, of which I was a part, as well as other resources available on the internet.

## Demo

| Functionality | Orientation Changes | Error Handling
|--|--|--|
| <img src="images/part1.gif" width="200px"> | <img src="images/part2.gif" width="200px"> | <img src="images/part3.gif" width="200px"> | 


:arrow_forward: [Video demo](https://youtu.be/MIb5NvRfV8k "Vídeo demo") :arrow_forward:

------------

## Remote Logging: Timber + Firebase Crashlytics

For security reasons, the `google-services.json` file, which enables remote logging, is not included in the project. To use this functionality, you should:

1. Change the package name of the project.
2. Create a Firebase project in the Firebase console.
3. Register the application by clicking on the Android icon in the project's overview section.
4. Download the google-services.json file and place it in the root directory of the Android application module.

[More information](https://proandroiddev.com/remote-logging-with-timber-and-firebase-realtime-database-a9dfbe66284c "More information")

------------

## Used Libraries

### Jetpack
- [android ktx](https://developer.android.com/kotlin/ktx "android ktx"): A set of Kotlin extensions included with Android Jetpack and other Android libraries.
- activity: activity: Access to Compose functions within the Activity class.
- [compose](https://developer.android.com/jetpack/androidx/releases/compose?hl=en "compose"): A modern, declarative UI toolkit for Android.
- [lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle "lifecycle"): Performs actions in response to changes in the lifecycle state of other components, such as activities and fragments.
- [navigation-compose](https://developer.android.com/jetpack/compose/navigation "navigation-compose"): Helps navigate between composable destinations.
- [compose-foundation](https://developer.android.com/jetpack/androidx/releases/compose-foundation "compose-foundation"):  Useful for creating a horizontal carousel in the detail view.

### Asynchronous Processing
- [coroutines](https://kotlinlang.org/docs/coroutines-overview.html "coroutines"): Perfect for executing asynchronous or non-blocking tasks.

### Image Handling
-  [coil](https://coil-kt.github.io/coil/ "coil"): Allows loading images hosted in external services for Android, backed by Kotlin Coroutines.

### Error Handling
- [arrow-core](https://arrow-kt.io/docs/core/ "arrow-core"): Complementary functional library for error handling.

### Dependency Injection
- [dagger-hilt](https://dagger.dev/hilt/ "dagger-hilt"): Provides a standard way to incorporate Dagger dependency injection into an Android application.

### REST API Integration
- [retrofit2](https://square.github.io/retrofit/ "retrofit2"): An HTTP client.
- [converter-gson](https://github.com/square/retrofit/tree/master/retrofit-converters/gson "converter-gson") :A converter that uses Gson for serialization to and from JSON.

### Logging
- [firebase-analytics](https://firebase.google.com/products/analytics/ "firebase-analytics"): Real-time crash reporting tool that helps prioritize and fix the most widespread issues based on their impact on real users.
- [timber](https://github.com/JakeWharton/timber "timber"): A logger with a small and extensible API that sits on top of the Android Log class.
- [okhttp3-logging-interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor "logging-interceptor"): An OkHttp interceptor that logs HTTP request and response data.

### Testing
- [junit](https://junit.org/junit4/ "junit"): An automation framework for unit and UI testing.
- [mockito](https://site.mockito.org/ "mockito"): Allows configuring our mock objects to return specific values when invoked.
- [mock-web-server:](https://github.com/square/okhttp/tree/master/mockwebserver "mock-web-server:"): Helps simulate a real server and write test cases.
- [espresso](https://developer.android.com/training/testing/espresso "espresso"): Enables creating automated UI tests.

------------

## UI Layer

1. Presentation Pattern: MVVM
2. Unidirectional Data Flow
3. MVVM Observables with State (Compose)
4. UI States: data class
5. UI Events: sealed class
6. Navigation

## Data Layer

1. Structure and Core Concepts
2. Repositories and Data Sources
3. Single Source of Truth
4. Error Handling

## Clean Architecture

Clean architecture in Android refers to an approach to design applications in a modular and maintainable manner, using specific design patterns to ensure separation of concerns, facilitate maintenance, and updates. Clean architecture improves code quality, testability, and scalability of the application.

<p align="center">
  <img src="/images/clean_architecture_en.png" height="300" width="300"/>
</p>


This project is modularized as follows:

- app
- data
- usecases
- domain

Additionally, it has two extra modules that cross-cut the rest:

- testShared
- buildSrc

------------

## Dependency Injection

Dagger Hilt as the recommended dependency injector by Google.

------------

## Testing

The testShared module shares resources for unit testing. Dependencies used in the project are managed in the buildSrc module.

1. Unit tests for repositories, use cases, and view models
2. Integration testing
3. UI testing with Hilt and MockWebServer

-------

## CI/CD

:construction: Pending :construction:

-------
