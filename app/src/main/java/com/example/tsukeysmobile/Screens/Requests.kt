package com.example.tsukeysmobile.Screens
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tsukeysmobile.DefaultText
import com.example.tsukeysmobile.requests
import com.example.tsukeysmobile.ui.theme.backgroundCol1
import com.example.tsukeysmobile.ui.theme.backgroundCol2
import java.util.*

data class Request(
    val id: UUID,
    var element: @Composable () -> Unit
)
{
    val visibleState = MutableTransitionState(false).apply { targetState = true }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun RequestsScreen()
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
        )
        {
            Text(text = "заявки", fontFamily = FontFamily(Font(com.example.tsukeysmobile.R.font.interblack)), fontSize = 70.sp, modifier = Modifier.offset(x = 20.dp))
        }
        LazyColumn(
            modifier = Modifier
                .weight(0.9f)
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
                .padding(vertical = 30.dp, horizontal = 20.dp),

        )
        {
            items(items = requests, key = { it.id })
            { request ->
                if (!request.visibleState.currentState && !request.visibleState.targetState) requests.remove(request)
                AnimatedVisibility(
                    modifier = Modifier
                        .animateItemPlacement(),
                    visibleState = request.visibleState,
                    enter = scaleIn(animationSpec = tween(durationMillis = 100)),
                    exit = slideOutHorizontally(animationSpec = tween(durationMillis = 100), targetOffsetX = { 0 }),
                )
                {
                    request.element()
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
            Column(horizontalAlignment = Alignment.CenterHorizontally)
            {
                Image(modifier = Modifier.size(25.dp)
                    .clickable {  },
                    painter = painterResource(id = com.example.tsukeysmobile.R.drawable.book),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                DefaultText(text = "бронь", size = 15, modifier = Modifier)
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally)
            {
                Image(modifier = Modifier.size(25.dp)
                    .clickable {  },
                    painter = painterResource(id = com.example.tsukeysmobile.R.drawable.requests),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                DefaultText(text = "заявки", size = 15, modifier = Modifier)
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally)
            {
                Image(modifier = Modifier.size(25.dp)
                    .clickable {  },
                    painter = painterResource(id = com.example.tsukeysmobile.R.drawable.profile),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                DefaultText(text = "профиль", size = 15, modifier = Modifier)
            }
        }
    }
}