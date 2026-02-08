package com.kaito.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kaito.app.ui.screen.container.ContainerScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        ContainerScreen()
    }
}