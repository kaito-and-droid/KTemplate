package com.kaito.kmoney.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.EmojiPeople
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kaito.kmoney.data.model.entity.User
import org.koin.compose.viewmodel.koinViewModel
import kotlin.random.Random
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    val vm = koinViewModel<SplashViewModel>()
    val users by vm.users.collectAsState()
    val names = mutableListOf(
        "Van de Sar",
        "Vidic",
        "Rio",
        "Brown",
        "Evra",
        "Scholes",
        "Keano",
        "Giggs",
        "Ronaldo",
        "Rooney",
        "Persies"
    )
    val rnd by remember {
        mutableStateOf(Random(1_000_000))
    }

    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Red)
            ) {
                Spacer(modifier = Modifier.height(statusBarHeight))
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    Text(text = "Demo")
                }
            }
        },
        floatingActionButton = {
            Icon(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.Red, CircleShape)
                    .clickable {
                        val idx = rnd.nextInt(names.size)
                        val name = names[idx]

                        val user = User(
                            id = Clock.System.now().epochSeconds,
                            name = name,
                            email = "${name}@mail.com"
                        )
                        vm.addUser(user)
                    },
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        },
    ) {
        if (users.isEmpty()) {
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    imageVector = Icons.Default.EmojiPeople,
                    contentDescription = null
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(users.size) { index ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .size(48.dp)
                                .background(Color.Gray, CircleShape)
                                .padding(4.dp),
                            imageVector = Icons.Default.VerifiedUser,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(text = users[index].name)
                            Text(text = users[index].email)
                        }
                    }
                }
            }
        }

    }
}