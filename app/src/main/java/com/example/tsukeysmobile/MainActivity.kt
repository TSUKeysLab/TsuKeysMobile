package com.example.tsukeysmobile

import android.os.Build
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tsukeysmobile.Screens.*
import com.example.tsukeysmobile.Screens.BookScreen
import com.example.tsukeysmobile.Screens.CabScreen
import com.example.tsukeysmobile.Screens.ProfileScreen
import com.example.tsukeysmobile.Screens.RegistrationScreen
import com.example.tsukeysmobile.Screens.Request
import com.example.tsukeysmobile.Screens.RequestsScreen
import com.example.tsukeysmobile.Views.AuthorizationScreen
import com.example.tsukeysmobile.ui.theme.TsuKeysMobileTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

val retrofit = Retrofit.Builder()
    .baseUrl("http://89.111.174.112:8181/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

var AUTHORIZE_TOKEN: String = ""

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TsuKeysMobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
//                    BlurredBackground()
                    Navigation()
                    println(AUTHORIZE_TOKEN)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnrememberedMutableState")
    @Composable
    fun Navigation()
    {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = com.example.tsukeysmobile.Navigation.Screen.AuthScreen.route)
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
                BookScreen(navController = navController)
            }
            composable(route = com.example.tsukeysmobile.Navigation.Screen.RegScreen.route)
            {
                RegistrationScreen(navController = navController)
            }
            composable(route = com.example.tsukeysmobile.Navigation.Screen.AuthScreen.route)
            {
                AuthorizationScreen(navController = navController)
            }
            composable(
                route = com.example.tsukeysmobile.Navigation.Screen.CabScreen.route + "/{dateAndLes}",
                arguments = listOf(
                    navArgument("dateAndLes") {
                        type = NavType.StringType
                        defaultValue = "Some Default"
                        nullable = true
                    },
                )
            ) { entry ->
                val dateAndLes = entry.arguments?.getString("dateAndLes")
                CabScreen(dateAndLes = dateAndLes!!, navController = navController)
            }
        }
    }
}
