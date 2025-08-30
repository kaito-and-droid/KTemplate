package com.kaito.kmoney

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kaito.kmoney.ui.Splash
import com.kaito.kmoney.ui.screen.splash.SplashScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val controller = rememberNavController()
    MaterialTheme {
        NavHost(navController = controller, startDestination = Splash) {
            composable<Splash> {
                SplashScreen()
            }
        }
    }
}