package com.example.tsukeysmobile.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.tsukeysmobile.R
import com.example.tsukeysmobile.Views.ReservationCard
import com.example.tsukeysmobile.ui.theme.backgroundCol1
import com.example.tsukeysmobile.ui.theme.backgroundCol2

@Composable
fun CabScreen(navController: NavController, dateAndLes: String) {


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
            DefaultText(text = "Бронь", size = 70, modifier = Modifier.offset(x = 20.dp, y = 12.dp))
            val lines = dateAndLes.split(" - ")
            Column(modifier = Modifier.padding(start = 20.dp)) {
                for(line in lines){
                    DefaultText(text = line, size = 20, modifier = Modifier.offset(x = 20.dp, y = 12.dp))
                }
            }


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

            LazyColumn {
                items(100) {
                    ReservationCard("1", dateAndLes)
                }

            }
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
                    painter = painterResource(id = R.drawable.book),
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
                    painter = painterResource(id = R.drawable.requests),
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
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                DefaultText(text = "профиль", size = 15, modifier = Modifier)
            }
        }
    }
}