package com.kaito.kmoney.ui.screen.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.compose.koinInject

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    val viewModel = koinInject<SplashViewModel>()
}