package com.kaito.kmoney.ui

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import kotlinx.serialization.Serializable

@Serializable
sealed interface Destiny {
    @Serializable
    data object Splash: Destiny
}

fun NavBackStackEntry?.getDestiny(): Destiny? {
    return this?.let {
        when {
            destination.hasRoute(Destiny.Splash::class) -> Destiny.Splash
            else -> null
        }
    }
}