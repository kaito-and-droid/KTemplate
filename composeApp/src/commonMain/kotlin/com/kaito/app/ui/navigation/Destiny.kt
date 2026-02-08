package com.kaito.app.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Destiny: NavKey

@Serializable
data object Splash: Destiny

@Serializable
data object Home: Destiny

@Serializable
data class Detail(val id: String): Destiny