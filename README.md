# Kotlin Multiplatform Project Template

This is a template for a Kotlin Multiplatform (KMP) project configured with essential dependencies for building modern, cross-platform applications.

## Project Overview

This project is set up to target multiple platforms (Android, iOS) using Kotlin Multiplatform. It includes a shared module for common logic and platform-specific modules for platform-specific implementations.

## Dependencies

The project includes the following dependencies to streamline development:

[Koin](https://insert-koin.io/): A lightweight dependency injection framework for Kotlin Multiplatform, used for managing dependencies across modules.

[Coil](https://github.com/coil-kt/coil): An image loading library for Android and other platforms, optimized for Kotlin with support for fast and efficient image loading.

[Jetpack Navigation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation.html): A navigation component for Android, enabling seamless navigation and deep linking in the app's UI.

[Ktor](https://ktor.io/): A flexible, asynchronous framework for making network requests, supporting HTTP clients and WebSockets across platforms.

[Napier](https://github.com/AAkira/Napier): A cross-platform logging library for Kotlin, providing a simple and consistent way to log messages across different platforms.

## Project Structure


```
project-root/
├── composeApp/
│   ├── src/commonMain/kotlin/
│   │   └── data
|   |   |   ├── model
|   |   |   ├── repository
|   |   |   ├── source
|   |   └── ui
|   |   |   ├── component
|   |   |   ├── screen 
│   ├── src/androidMain/kotlin/
│   ├── src/iosMain/kotlin/
├── iosApp/
│   └── iOS-specific code
└── build.gradle.kts
```

## Koin init

***Android***
```
class KApp: Application() {

    override fun onCreate() {
        super.onCreate()

        Napier.base(DebugAntilog())
        startKoin {
            androidContext(this@KApp)
            modules(appModule)
            androidLogger()
        }
    }
}
```

***Ios***
```
// MainViewController.kt
fun initKoin() {
    startKoin {
        modules(appModule)
    }
}

fun initNapier() {
    Napier.base(DebugAntilog())
}

// iOSApp.swift
@main
struct iOSApp: App {
  init() {
    MainViewControllerKt.doInitNapier()
    MainViewControllerKt.doInitKoin()
  }
}
```
