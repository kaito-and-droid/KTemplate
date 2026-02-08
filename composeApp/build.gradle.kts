plugins {
    alias(libs.plugins.kotlin.mp)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.mp)
    alias(libs.plugins.android.kmp.library)
    alias(libs.plugins.kotlin.serializer)
}

kotlin {
    jvmToolchain(21)

    androidLibrary {
        namespace = "com.kaito.core"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
        androidResources.enable = true
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            api(libs.jb.compose.preview)
            api(libs.androidx.activity.compose)
            api(libs.koin.android)
            implementation(libs.ktor.okhttp)
        }
        commonMain.dependencies {
            implementation(libs.jb.compose.rt)
            implementation(libs.jb.compose.foundation)
            implementation( libs.jb.compose.material3)
            implementation(libs.jb.compose.ui)
            implementation(libs.jb.compose.resource)
            implementation(libs.jb.compose.preview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.jb.nav3)
            implementation(libs.jb.json)
            implementation(libs.jb.time)

            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.viewmodel.nav)

            implementation(libs.ktor.core)
            implementation(libs.ktor.content)
            implementation(libs.ktor.json)

            implementation(libs.coil.core)
            implementation(libs.coil.okhttp)

            api(libs.napier)
        }

        iosMain.dependencies {
            implementation(libs.ktor.darwin)
        }
    }
}
