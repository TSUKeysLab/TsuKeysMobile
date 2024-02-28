package com.example.tsukeysmobile

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tsukeysmobile.Screens.ProfileScreen
import com.example.tsukeysmobile.Screens.Request
import com.example.tsukeysmobile.Screens.RequestsScreen
import com.example.tsukeysmobile.ui.theme.TsuKeysMobileTheme
import com.example.tsukeysmobile.ui.theme.requestRepeatable
import com.example.tsukeysmobile.ui.theme.requestSingle
import java.util.*

val requests : MutableList<Request> = mutableStateListOf(Request(UUID.randomUUID()) {
    Divider(modifier = Modifier
        .fillMaxWidth(0.9f)
        .offset(x = 20.dp)
        .padding(vertical = 50.dp), color = Color.Black, thickness = 6.dp)
})
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TsuKeysMobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    requests.add(0, Request(UUID.randomUUID()) {
                        RequestCard(
                            id = UUID.randomUUID(),
                            status = "Approved",
                            date = "14.02",
                            cab = "227",
                            time = "18:25-20:00",
                            type = requestSingle,
                        )
                    })
                    requests.add(0, Request(UUID.randomUUID()) {
                        RequestCard(
                            id = UUID.randomUUID(),
                            status = "Pending",
                            date = "14.02",
                            cab = "227",
                            time = "18:25-20:00",
                            type = requestSingle,
                        )
                    })
                    requests.add(Request(UUID.randomUUID()) {
                        RequestCard(
                            id = UUID.randomUUID(),
                            status = "",
                            date = "вт",
                            cab = "227",
                            time = "18:25-20:00",
                            type = requestRepeatable,
                        )
                    })
                    Navigation()
                }
            }
        }
    }
    @SuppressLint("UnrememberedMutableState")
    @Composable
    fun Navigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = com.example.tsukeysmobile.Navigation.Screen.RequestsScreen.route)
        {
            composable(route = com.example.tsukeysmobile.Navigation.Screen.RequestsScreen.route)
            {
                RequestsScreen(navController = navController, requests = requests)
            }
            composable(route = com.example.tsukeysmobile.Navigation.Screen.ProfileScreen.route)
            {
                ProfileScreen(navController = navController)
            }
            composable(route = com.example.tsukeysmobile.Navigation.Screen.BookScreen.route)
            {
                ProfileScreen(navController = navController)
            }
        }
    }
}
