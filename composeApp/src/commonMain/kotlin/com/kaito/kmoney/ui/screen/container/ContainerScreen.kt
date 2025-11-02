package com.kaito.kmoney.ui.screen.container

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kaito.kmoney.ui.Destiny
import com.kaito.kmoney.ui.getDestiny
import com.kaito.kmoney.ui.screen.splash.SplashScreen

@Composable
fun ContainerScreen(
    controller: NavHostController = rememberNavController()
) {
    val entry by controller.currentBackStackEntryAsState()
    val destiny = entry.getDestiny()

    Scaffold { innerPad ->
        NavHost(
            navController = controller,
            startDestination = Destiny.Splash
        ) {
            composable<Destiny.Splash> {
                SplashScreen()
            }
        }
    }
}