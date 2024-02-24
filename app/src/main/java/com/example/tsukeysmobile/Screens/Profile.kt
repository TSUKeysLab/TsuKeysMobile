package com.example.tsukeysmobile.Screens
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.tsukeysmobile.DefaultText
import com.example.tsukeysmobile.Navigation.Screen
import com.example.tsukeysmobile.Views.PopupWindowDialog
import com.example.tsukeysmobile.ui.theme.backgroundCol1
import com.example.tsukeysmobile.ui.theme.backgroundCol2
import com.example.tsukeysmobile.ui.theme.darkGreen
import com.example.tsukeysmobile.ui.theme.profileCol
import java.util.*

@Composable
fun ProfileScreen(navController: NavController)
{
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
            DefaultText(text = "профиль", size = 70, modifier = Modifier.offset(x = 20.dp, y = 12.dp))
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
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            )
            {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.83f)
                        .fillMaxHeight(0.5f)
                        .background(color = profileCol),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                )
                {
                    DefaultText(text = "Иван Иванов", size = 40, modifier = Modifier.padding(10.dp))
                    Row(
                        horizontalArrangement = Arrangement.Center
                    )
                    {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .wrapContentSize()
                                .background(color = Color.White, shape = RoundedCornerShape(50))
                                .border(
                                    width = 3.dp,
                                    shape = RoundedCornerShape(50),
                                    color = Color.Black
                                )
                        )
                        {
                            DefaultText(text = "Студент", size = 20, modifier = Modifier.padding(vertical = 7.dp, horizontal = 15.dp), color = darkGreen)
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .wrapContentSize()
                                .background(color = Color.White, shape = RoundedCornerShape(50))
                                .border(
                                    width = 3.dp,
                                    shape = RoundedCornerShape(50),
                                    color = Color.Black
                                )
                        )
                        {
                            DefaultText(text = "972202", size = 20, modifier = Modifier.padding(vertical = 7.dp, horizontal = 15.dp), color = Color.Black)
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .offset(x = 0.dp, y = (-80).dp)
                    .fillMaxHeight(0.55f)
                    .fillMaxWidth(1f)
                    .background(color = profileCol, shape = RoundedCornerShape(50)),
                contentAlignment = Alignment.Center
            )
            {
                Image(modifier = Modifier.fillMaxSize(0.9f),
                    painter = painterResource(id = com.example.tsukeysmobile.R.drawable.profilebig),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .absoluteOffset(y = (-25).dp),
                contentAlignment = Alignment.BottomEnd
            )
            {
                PopupWindowDialog()
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
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.alpha(0.5f))
            {
                Image(modifier = Modifier
                    .size(25.dp)
                    .clickable { navController.navigate(Screen.BookScreen.withArgs()) },
                    painter = painterResource(id = com.example.tsukeysmobile.R.drawable.book),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                DefaultText(text = "бронь", size = 15, modifier = Modifier)
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.alpha(0.5f))
            {
                Image(modifier = Modifier
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

            Column(horizontalAlignment = Alignment.CenterHorizontally)
            {
                Image(modifier = Modifier
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