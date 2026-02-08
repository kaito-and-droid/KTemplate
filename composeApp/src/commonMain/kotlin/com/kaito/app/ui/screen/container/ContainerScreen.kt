package com.kaito.app.ui.screen.container

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.kaito.app.ui.navigation.Detail
import com.kaito.app.ui.navigation.Home
import com.kaito.app.ui.navigation.Splash
import com.kaito.app.ui.navigation.navConfig
import com.kaito.app.ui.screen.detail.DetailScreen
import com.kaito.app.ui.screen.home.HomeScreen
import com.kaito.app.ui.screen.splash.SplashScreen

@Composable
fun ContainerScreen() {
    val backstack = rememberNavBackStack(navConfig, Splash)
    Scaffold { innerPad ->
        NavDisplay(
            modifier = Modifier.padding(innerPad),
            backStack = backstack,
            onBack = {
                if (backstack.size > 1) {
                    backstack.removeLastOrNull()
                }
            },
            entryProvider = entryProvider {
                entry<Splash> {
                    SplashScreen {
                        backstack.removeLastOrNull()
                        backstack.add(Home)
                    }
                }
                entry<Home> {
                    HomeScreen {
                        backstack.add(Detail(it))
                    }
                }
                entry<Detail> { key ->
                    DetailScreen(key.id)
                }
            }
        )
    }
}