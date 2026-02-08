# KTemplate - Kotlin Multiplatform Project Template

A production-ready template repository for bootstrapping Kotlin Multiplatform (KMP) projects with modern architecture and essential dependencies pre-configured.

> **Purpose**: This repository serves as a starting point for creating new KMP applications targeting Android and iOS platforms. Clone it, rename the package, and start building your app.

## Why Use This Template?

- ✅ **Zero Configuration**: Pre-configured build scripts and dependencies
- ✅ **Modern Stack**: Latest Kotlin, Compose Multiplatform, and Navigation 3
- ✅ **Type-Safe Navigation**: State-based navigation with serializable routes
- ✅ **Dependency Injection**: Koin configured for multiplatform
- ✅ **Networking Ready**: Ktor client with JSON serialization
- ✅ **Clean Architecture**: Organized folder structure following best practices

## How to Use This Template

1. **Clone or Use Template**
   ```bash
   git clone https://github.com/your-username/KTemplate.git MyApp
   cd MyApp
   ```

2. **Rename Package**
   - Change `com.kaito.app` to your package name across all files
   - Update `namespace` in `androidApp/build.gradle.kts`
   - Update `applicationId` in `androidApp/build.gradle.kts`

3. **Configure Project**
   - Update `rootProject.name` in `settings.gradle.kts`
   - Update `baseName` in `composeApp/build.gradle.kts:25`
   - Customize app name in `androidApp/src/main/res/values/strings.xml`

4. **Start Building**
   - Add your features in `composeApp/src/commonMain/kotlin/`
   - Register ViewModels in `ui/di/UiModule.kt`
   - Add routes in `ui/navigation/Destiny.kt`

## What's Included

### Core Dependencies

| Dependency | Version | Purpose |
|------------|---------|---------|
| [Kotlin](https://kotlinlang.org/) | 2.3.0 | Programming language |
| [Compose Multiplatform](https://www.jetbrains.com/compose-multiplatform/) | 1.10.0 | UI framework |
| [Navigation 3](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation.html) | 1.1.0-alpha01 | Type-safe navigation |
| [Koin](https://insert-koin.io/) | 4.1.1 | Dependency injection |
| [Ktor Client](https://ktor.io/) | 3.4.0 | HTTP networking |
| [Coil 3](https://coil-kt.github.io/coil/) | 3.3.0 | Image loading |
| [Napier](https://github.com/AAkira/Napier) | 2.7.1 | Cross-platform logging |
| [kotlinx-serialization](https://github.com/Kotlin/kotlinx.serialization) | 1.10.0 | JSON serialization |
| [kotlinx-datetime](https://github.com/Kotlin/kotlinx-datetime) | 0.7.1 | Date/time utilities |

### Features Out of the Box

- 🎨 **Material 3 Theming** with Compose Multiplatform
- 🧭 **State-Based Navigation** using Navigation 3
- 💉 **Dependency Injection** with Koin modules
- 🌐 **HTTP Client** configured with ContentNegotiation
- 🖼️ **Image Loading** with Coil 3
- 📝 **Logging** via Napier (compatible across platforms)
- ⚡ **Build Optimization** (configuration cache, parallel builds)

## Project Structure

```
KTemplate/
├── composeApp/                  # Shared KMP library
│   ├── src/
│   │   ├── commonMain/          # Shared code (Android + iOS)
│   │   │   ├── kotlin/
│   │   │   │   └── com/kaito/app/
│   │   │   │       ├── App.kt                    # Main entry point
│   │   │   │       ├── KoinHelper.kt             # DI modules
│   │   │   │       ├── data/
│   │   │   │       │   ├── di/DataModule.kt      # Data layer DI
│   │   │   │       │   ├── model/                # Data models
│   │   │   │       │   ├── repository/           # Repositories
│   │   │   │       │   └── source/remote/        # Ktor client
│   │   │   │       └── ui/
│   │   │   │           ├── navigation/           # Routes & NavConfig
│   │   │   │           ├── screen/               # Feature screens
│   │   │   │           │   ├── splash/
│   │   │   │           │   ├── home/
│   │   │   │           │   ├── detail/
│   │   │   │           │   └── container/        # NavHost
│   │   │   │           ├── component/            # Reusable components
│   │   │   │           └── di/UiModule.kt        # UI layer DI
│   │   │   └── composeResources/
│   │   ├── androidMain/         # Android-specific code
│   │   └── iosMain/             # iOS-specific code
│   └── build.gradle.kts
├── androidApp/                  # Android application wrapper
│   ├── src/main/
│   │   ├── kotlin/
│   │   │   ├── MainActivity.kt
│   │   │   └── KApp.kt          # Application class (Koin init)
│   │   ├── AndroidManifest.xml
│   │   └── res/
│   └── build.gradle.kts
├── iosApp/                      # iOS application wrapper
│   └── iOSApp.swift             # Koin/Napier init
├── gradle/libs.versions.toml    # Centralized dependency versions
└── settings.gradle.kts
```

## Quick Start Guide

### Prerequisites
- JDK 17 or higher
- Android Studio Ladybug or later
- Xcode 15+ (for iOS development)
- CocoaPods (for iOS dependencies)

### Running the App

**Android**
```bash
./gradlew :androidApp:installDebug
```

**iOS**
1. Open `iosApp/iosApp.xcworkspace` in Xcode
2. Select target device/simulator
3. Run (⌘R)

## Architecture Overview

### Navigation (Type-Safe with Navigation 3)

```kotlin
// Define routes in Destiny.kt
@Serializable
sealed interface Destiny: NavKey

@Serializable data object Home: Destiny
@Serializable data class Detail(val id: String): Destiny

// Navigate in your composables
backstack.add(Detail(id = "123"))
backstack.removeLastOrNull()  // Go back
```

### Dependency Injection (Koin)

**Platform Initialization**

*Android* (`androidApp/src/main/kotlin/com/kaito/app/KApp.kt`):
```kotlin
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

*iOS* (`composeApp/src/iosMain/kotlin/com/kaito/app/MainViewController.kt`):
```kotlin
fun initKoin() {
    startKoin { modules(appModule) }
}

fun initNapier() {
    Napier.base(DebugAntilog())
}
```

*iOS Swift* (`iosApp/iOSApp.swift`):
```swift
@main
struct iOSApp: App {
    init() {
        MainViewControllerKt.doInitNapier()
        MainViewControllerKt.doInitKoin()
    }
}
```

**Injecting Dependencies**

```kotlin
@Composable
fun MyScreen() {
    // Inject ViewModel
    val viewModel = koinInject<MyViewModel>()

    // Inject any dependency
    val httpClient = koinInject<HttpClient>()
}
```

### HTTP Networking (Ktor)

The template includes a pre-configured Ktor client:

```kotlin
// Already configured in data/source/remote/Client.kt
val client = get<HttpClient>()  // From Koin

// Make requests
val response: MyData = client.get("https://api.example.com/data")
```

## Customization Guide

### Adding a New Screen

1. **Create the route** in `ui/navigation/Destiny.kt`:
   ```kotlin
   @Serializable
   data class Profile(val userId: String): Destiny
   ```

2. **Register in NavConfig** (`ui/navigation/NavConfig.kt`):
   ```kotlin
   subclass(Profile::class, Profile.serializer())
   ```

3. **Add composable** in `ui/screen/container/ContainerScreen.kt`:
   ```kotlin
   entry<Profile> { key ->
       ProfileScreen(userId = key.userId)
   }
   ```

4. **Navigate to it**:
   ```kotlin
   backstack.add(Profile(userId = "123"))
   ```

### Adding a ViewModel

1. **Create ViewModel**:
   ```kotlin
   class ProfileViewModel : ViewModel() {
       // Your logic here
   }
   ```

2. **Register in Koin** (`ui/di/UiModule.kt`):
   ```kotlin
   val uiModule = module {
       viewModelOf(::ProfileViewModel)
   }
   ```

3. **Inject in screen**:
   ```kotlin
   val viewModel = koinInject<ProfileViewModel>()
   ```

## Build Configuration

### Gradle Optimization

The template includes optimized Gradle settings in `gradle.properties`:
- Configuration cache enabled
- Build caching enabled
- Parallel execution enabled
- 4GB max heap size

### Version Catalogs

All dependencies are managed via `gradle/libs.versions.toml` for centralized version control.

## Platform-Specific Notes

### Android
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 36
- **Compile SDK**: 36
- **JVM Toolchain**: 17
- **Namespace**: `com.kaito.app` (update this)

### iOS
- **Deployment Target**: Configure in Xcode
- **Framework**: ComposeApp (static)
- **Supported Architectures**: x64, ARM64, Simulator ARM64

## Next Steps

After cloning this template:

1. ✅ Rename package from `com.kaito.app` to your domain
2. ✅ Update app name and branding
3. ✅ Configure signing for Android
4. ✅ Set up iOS provisioning profiles
5. ✅ Add your business logic in `data/repository/`
6. ✅ Create your UI screens
7. ✅ Set up CI/CD (optional)
8. ✅ Add Firebase/Analytics (optional)

## Contributing

This is a template repository. Feel free to fork and customize for your own needs. If you find improvements that would benefit the template itself, PRs are welcome!

## License

This template is provided as-is for creating new KMP projects. Use it however you like.
