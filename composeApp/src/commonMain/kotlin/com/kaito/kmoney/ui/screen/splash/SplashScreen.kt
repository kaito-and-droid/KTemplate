package com.kaito.kmoney.ui.screen.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    val viewModel = koinInject<SplashViewModel>()
    val appSettings by viewModel.appSettings.collectAsState()
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.secondary)
            ) {
                Spacer(modifier = Modifier.height(statusBarHeight))
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Demo",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Enable Notification",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.weight(1F))

                Switch(
                    checked = appSettings.isEnableNotification,
                    onCheckedChange = { value ->
                        viewModel.setEnableNoti(value)
                    }
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Premium",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.weight(1F))

                Switch(
                    checked = appSettings.isPremium,
                    onCheckedChange = { value ->
                        viewModel.setPremium(value)
                    }
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Dark Theme",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.weight(1F))

                Switch(
                    checked = appSettings.isDarkTheme,
                    onCheckedChange = { value ->
                        viewModel.setDarkTheme(value)
                    }
                )
            }
        }
    }
}