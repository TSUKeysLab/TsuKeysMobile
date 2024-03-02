package com.example.tsukeysmobile.Views

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tsukeysmobile.DefaultText
import com.example.tsukeysmobile.R
import com.example.tsukeysmobile.Screens.Request
import com.example.tsukeysmobile.ui.theme.*
import java.util.*

@Composable
fun OutcomeRequestCard(
    Sender: String,
    Cab: String,
    Till: String,
)
{
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .background(color = Color.Gray, shape = RoundedCornerShape(10.dp))
        .padding(5.dp))
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp))
        {
            DefaultText(text = "You", size = 20, modifier = Modifier, color = Color.White)
            Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = null, tint = Color.Red, modifier = Modifier.padding(horizontal = 5.dp))
            Image(painter = painterResource(id = R.drawable.sender), contentDescription = null, modifier = Modifier
                .size(30.dp)
                .offset(y = -3.dp)
                .padding(horizontal = 5.dp))
            DefaultText(text = Sender, size = 20, modifier = Modifier, color = Color.White)
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
            .padding(bottom = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween)
        {
            Box(modifier = Modifier
                .wrapContentSize()
                .background(color = requestSingle, shape = RoundedCornerShape(50)))
            {
                DefaultText(text = "кабинет$Cab", size = 20, modifier = Modifier.padding(horizontal = 5.dp), color = Color.White)
            }
            Box(modifier = Modifier
                .wrapContentSize()
                .background(color = requestRepeatable, shape = RoundedCornerShape(50)))
            {
                DefaultText(text = "до $Till", size = 20,modifier = Modifier.padding(horizontal = 5.dp), color = Color.White)
            }
        }

    }
}

@Composable
fun IncomeRequestCard(
    Sender: String,
    Cab: String,
    Till: String,
)
{
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .background(color = Color.Gray, shape = RoundedCornerShape(10.dp))
        .padding(5.dp))
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp))
        {
            Image(painter = painterResource(id = R.drawable.sender), contentDescription = null, modifier = Modifier
                .size(30.dp)
                .offset(y = -3.dp)
                .padding(horizontal = 5.dp))
            DefaultText(text = Sender, size = 20, modifier = Modifier, color = Color.White)
            Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = null, tint = Color.Green, modifier = Modifier.padding(horizontal = 5.dp))
            DefaultText(text = "You", size = 20, modifier = Modifier, color = Color.White)
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
            .padding(bottom = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween)
        {
            Box(modifier = Modifier
                .wrapContentSize()
                .background(color = requestSingle, shape = RoundedCornerShape(50)))
            {
                DefaultText(text = "кабинет$Cab", size = 20, modifier = Modifier.padding(horizontal = 5.dp), color = Color.White)
            }
            Box(modifier = Modifier
                .wrapContentSize()
                .background(color = requestRepeatable, shape = RoundedCornerShape(50)))
            {
                DefaultText(text = "до $Till", size = 20,modifier = Modifier.padding(horizontal = 5.dp), color = Color.White)
            }
        }
        
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun ShowOutComing(
    outcoming: MutableList<Request>,
)
{
    Box(
        modifier = Modifier
            .fillMaxSize().padding(bottom = 20.dp),
        contentAlignment = Alignment.BottomCenter
    )
    {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp, horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        )
        {
            items(items = outcoming, key = { it.id })
            { request ->
                if (!request.visibleState.currentState && !request.visibleState.targetState) outcoming.remove(request)
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
        KeysMenuCreateKey()
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun ShowInComing(
    incoming: MutableList<Request>,
)
{
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp, horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
        )
    {
        items(items = incoming, key = { it.id })
        { request ->
            if (!request.visibleState.currentState && !request.visibleState.targetState) incoming.remove(request)
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
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun KeysMenu()
{
    val openDialog = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .wrapContentSize()
            .background(color = Color.Black, shape = RoundedCornerShape(40))
            .border(width = 5.dp, shape = RoundedCornerShape(40), color = Color.White)
            .clickable { openDialog.value = !openDialog.value },
        horizontalArrangement = Arrangement.Center
    )
    {
        Image(modifier = Modifier
            .size(40.dp)
            .offset(x = 15.dp, y = 10.dp),
            painter = painterResource(id = R.drawable.key),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        DefaultText(text = "ключи", size = 40, modifier = Modifier.padding(vertical = 5.dp, horizontal = 20.dp), color = Color.White)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 5.dp),
        contentAlignment = Alignment.Center
    )
    {

        if (openDialog.value)
        {
            Box(modifier = Modifier
                .background(color = gray, shape = RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .border(1.dp, Color.Black, shape = RoundedCornerShape(10.dp)))
            {
                Column(modifier = Modifier.fillMaxSize())
                {
                    Row(modifier = Modifier
                        .background(
                            color = Color.Black,
                            shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                        )
                        .fillMaxWidth()
                        .padding(10.dp)
                        .wrapContentHeight(),
                        horizontalArrangement = Arrangement.SpaceBetween)
                    {
                        Box(modifier = Modifier
                            .background(color = Color.Gray, shape = RoundedCornerShape(50))
                            .size(30.dp), contentAlignment = Alignment.Center)
                        {
                            DefaultText(text = "?", size = 20, modifier = Modifier, color = Color.White)
                        }
                        Box(modifier = Modifier
                            .clickable { openDialog.value = !openDialog.value }
                            .background(color = Color.Gray)
                            .size(30.dp), contentAlignment = Alignment.Center)
                        {
                            DefaultText(text = "X", size = 20, modifier = Modifier, color = Color.White)
                        }
                    }
                    var tabIndex by remember { mutableStateOf(0) }

                    val tabs = listOf("входящие", "исходящие")

                    val incoming : MutableList<Request> = mutableStateListOf()

                    val outcoming : MutableList<Request> = mutableStateListOf()

                    Column(modifier = Modifier.fillMaxWidth())
                    {
                        TabRow(selectedTabIndex = tabIndex, backgroundColor = darkGray, contentColor = Color.White)
                        {
                            tabs.forEachIndexed{ index, title ->
                                Tab(text = { Text(title) },
                                    selected = tabIndex == index,
                                    onClick = { tabIndex = index },
                                    selectedContentColor = lightGray,
                                    unselectedContentColor = Color.Gray,
                                    modifier = Modifier.background(shape = RoundedCornerShape(bottomStart = 20.dp,bottomEnd = 20.dp), color = darkGray)
                                )
                            }
                        }
                        when (tabIndex)
                        {
                            0 -> ShowInComing(incoming)
                            1 -> ShowOutComing(outcoming)
                        }
                    }
                }
            }
        }
    }
}