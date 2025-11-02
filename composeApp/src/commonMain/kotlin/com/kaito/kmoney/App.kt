package com.kaito.kmoney

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.kaito.kmoney.ui.screen.container.ContainerScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        ContainerScreen()
    }
}