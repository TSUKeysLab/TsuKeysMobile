package com.example.tsukeysmobile

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
import java.util.*

val requests : MutableList<Request> = mutableStateListOf(Request(UUID.randomUUID()) {
    Divider(modifier = Modifier.fillMaxWidth(0.9f).offset(x = 20.dp).padding(vertical = 50.dp), color = Color.Black, thickness = 6.dp)
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
                    Navigation()
                }
            }
        }
    }
    @Composable
    fun Navigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = com.example.tsukeysmobile.Navigation.Screen.RequestsScreen.route)
        {
            composable(route = com.example.tsukeysmobile.Navigation.Screen.RequestsScreen.route)
            {
                RequestsScreen(navController = navController)
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
