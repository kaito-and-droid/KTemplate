plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.kotlin.mp) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.compose.mp) apply false
    alias(libs.plugins.android.app) apply false
    alias(libs.plugins.android.kmp.library) apply false
    alias(libs.plugins.kotlin.serializer) apply false
    alias(libs.plugins.kotlin.android) apply false
}