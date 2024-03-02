package com.example.tsukeysmobile.Screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.tsukeysmobile.DefaultText
import com.example.tsukeysmobile.Navigation.Screen
import com.example.tsukeysmobile.Views.BookCard
import com.example.tsukeysmobile.ui.theme.backgroundCol1
import com.example.tsukeysmobile.ui.theme.backgroundCol2




@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookScreen(navController: NavController) {



    Column(
        modifier = Modifier.fillMaxSize()
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 80.dp)
                .background(color = Color.Black)
                .zIndex(1f)
        )
        {
            DefaultText(text = "бронь", size = 70, modifier = Modifier.offset(x = 20.dp, y = 12.dp))
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            backgroundCol1,
                            backgroundCol2,
                            backgroundCol2
                        )
                    )
                )
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center,
        )
        {
            BookCard(navController = navController)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 80.dp)
                .background(color = Color.Black),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            )
            {
                Image(
                    modifier = Modifier
                        .size(25.dp)
                        .clickable { navController.navigate(Screen.BookScreen.withArgs()) },
                    painter = painterResource(id = com.example.tsukeysmobile.R.drawable.book),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                DefaultText(text = "бронь", size = 15, modifier = Modifier)
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.alpha(0.5f)
            )
            {
                Image(
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            navController.navigate(Screen.RequestsScreen.withArgs())
                        },
                    painter = painterResource(id = com.example.tsukeysmobile.R.drawable.requests),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                DefaultText(text = "заявки", size = 15, modifier = Modifier)
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.alpha(0.5f)
            )
            {
                Image(
                    modifier = Modifier
                        .size(25.dp)
                        .clickable { navController.navigate(Screen.ProfileScreen.withArgs()) },
                    painter = painterResource(id = com.example.tsukeysmobile.R.drawable.profile),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                DefaultText(text = "профиль", size = 15, modifier = Modifier)
            }
        }
    }
}