package com.example.tsukeysmobile.Views

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tsukeysmobile.DefaultText
import com.example.tsukeysmobile.R
import com.example.tsukeysmobile.RequestCard
import com.example.tsukeysmobile.Requests.AUTHORIZE_TOKEN
import com.example.tsukeysmobile.Screens.Request
import com.example.tsukeysmobile.Screens.requestService
import com.example.tsukeysmobile.Screens.requests
import com.example.tsukeysmobile.ui.theme.*
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OutcomeRequestCard(
    id: String,
    Sender: String,
    Cab: String,
    Till: String,
    Status: String,
)
{
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .background(color = Color.Gray, shape = RoundedCornerShape(10.dp))
        .padding(5.dp)
        .pointerInput(Unit)
        {
            detectTapGestures(
                onPress = {
                    if (!openKeyOutRequestActionsMenu) {
                        keyOutRequest.value = outcoming.find { it.id == id }!!
                        openKeyOutRequestActionsMenu = true
                        status.value = Status
                    }
                }
            )
        })
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween)
        {
            DefaultText(text = "You", size = 17, modifier = Modifier, color = Color.White)
            Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = null, tint = Color.Red, modifier = Modifier)
            Image(painter = painterResource(id = R.drawable.sender), contentDescription = null, modifier = Modifier
                .size(20.dp)
            )
            DefaultText(text = Sender, size = 17, modifier = Modifier, color = Color.White)

            val color: Color
            if (Status == "Pending") color = gray else if (Status == "Approved") color = Color.Green else color = Color.Red
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 20.dp)
                    .background(color = color, shape = RoundedCornerShape(50))
            )
            {
                DefaultText(text = Status, size = 15, modifier = Modifier.padding(vertical = 2.dp, horizontal = 5.dp))
            }
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
                DefaultText(text = "кабинет $Cab", size = 20, modifier = Modifier.padding(horizontal = 5.dp), color = Color.White)
            }
            Box(modifier = Modifier
                .wrapContentSize()
                .background(color = requestRepeatable, shape = RoundedCornerShape(50)))
            {
                DefaultText(text = "до ${Till.slice(0..4)}", size = 20,modifier = Modifier.padding(horizontal = 5.dp), color = Color.White)
            }
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun IncomeRequestCard(
    id: String,
    Sender: String,
    Cab: String,
    Till: String,
    Status: String,
)
{
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .background(color = Color.Gray, shape = RoundedCornerShape(10.dp))
        .padding(5.dp)
        .pointerInput(Unit)
        {
            detectTapGestures(
                onPress = {
                    if (!openKeyInRequestActionsMenu) {
                        keyInRequest.value = outcoming.find { it.id == id }!!
                        openKeyInRequestActionsMenu = true
                        status.value = Status
                    }
                }
            )
        })
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
        , horizontalArrangement = Arrangement.SpaceBetween)
        {
            Image(painter = painterResource(id = R.drawable.sender), contentDescription = null, modifier = Modifier
                .size(20.dp))
            DefaultText(text = Sender, size = 17, modifier = Modifier, color = Color.White)
            Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = null, tint = Color.Green, modifier = Modifier.padding(horizontal = 5.dp))
            DefaultText(text = "You", size = 17, modifier = Modifier, color = Color.White)

            val color: Color
            if (Status == "Pending") color = gray else if (Status == "Approved") color = Color.Green else color = Color.Red
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 20.dp)
                    .background(color = color, shape = RoundedCornerShape(50))
            )
            {
                DefaultText(text = Status, size = 15, modifier = Modifier.padding(vertical = 2.dp, horizontal = 5.dp))
            }
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
                DefaultText(text = "кабинет $Cab", size = 20, modifier = Modifier.padding(horizontal = 5.dp), color = Color.White)
            }
            Box(modifier = Modifier
                .wrapContentSize()
                .background(color = requestRepeatable, shape = RoundedCornerShape(50)))
            {
                DefaultText(text = "до ${Till.slice(0..4)}", size = 20,modifier = Modifier.padding(horizontal = 5.dp), color = Color.White)
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
            .fillMaxSize()
            .padding(bottom = 20.dp),
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

var openKeysMenu by mutableStateOf(false)
var incoming by mutableStateOf<MutableList<Request>>(mutableStateListOf())
var outcoming by mutableStateOf<MutableList<Request>>(mutableStateListOf())
lateinit var context: Context

var status: MutableState<String> = mutableStateOf("")

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnrememberedMutableState")
@Composable
fun KeysMenu()
{
    context = LocalContext.current
    val KeysMenuAlpha by animateFloatAsState(
        targetValue = if (openKeysMenu) 1f else 0f,
        animationSpec = tween(durationMillis = 300)
    )

    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center)
    {

        val blurAmount by animateFloatAsState(
            targetValue = if (openKeyInRequestActionsMenu || openKeyOutRequestActionsMenu) 25f else 0f,
            animationSpec = tween(durationMillis = 300)
        )

        Box(
            modifier = Modifier.blur(blurAmount.dp)
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.7f)
                .padding(horizontal = 5.dp),
            contentAlignment = Alignment.Center
        )
        {
            if (openKeysMenu) {
                Box(
                    modifier = Modifier
                        .alpha(KeysMenuAlpha)
                        .background(color = gray, shape = RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                        .fillMaxHeight(0.8f)
                        .border(1.dp, Color.Black, shape = RoundedCornerShape(10.dp))
                )
                {
                    Column(modifier = Modifier.fillMaxSize())
                    {
                        Row(
                            modifier = Modifier
                                .background(
                                    color = Color.Black,
                                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                                )
                                .fillMaxWidth()
                                .padding(10.dp)
                                .wrapContentHeight(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        )
                        {
                            Box(
                                modifier = Modifier
                                    .background(color = darkGray, shape = RoundedCornerShape(50))
                                    .size(30.dp), contentAlignment = Alignment.Center
                            )
                            {
                                DefaultText(
                                    text = "?",
                                    size = 20,
                                    modifier = Modifier,
                                    color = Color.White
                                )
                            }
                            Box(modifier = Modifier
                                .clickable { openKeysMenu = !openKeysMenu }
                                .background(color = darkGray)
                                .size(30.dp), contentAlignment = Alignment.Center)
                            {
                                DefaultText(
                                    text = "X",
                                    size = 20,
                                    modifier = Modifier,
                                    color = Color.White
                                )
                            }
                        }
                        var tabIndex by remember { mutableStateOf(0) }

                        val tabs = listOf("входящие", "исходящие")

                        val coroutineScope = rememberCoroutineScope()

                        LaunchedEffect(Unit) {
                            coroutineScope.launch {
                                var response =
                                    requestService.getKeyRequests(AUTHORIZE_TOKEN, "Recipient")
                                        .awaitResponse().body()
                                if (response != null) incoming = response.map {
                                    Request(
                                        id = it.requestId,
                                        {
                                            IncomeRequestCard(
                                                id = it.requestId,
                                                Sender = it.keyOwnerFullName,
                                                Cab = it.classroomNumber,
                                                Till = it.endOfRequest,
                                                Status = it.status
                                            )
                                        })
                                }.toMutableStateList()

                                response = requestService.getKeyRequests(AUTHORIZE_TOKEN, "Owner")
                                    .awaitResponse().body()
                                if (response != null) outcoming = response.map {
                                    Request(
                                        id = it.requestId,
                                        {
                                            OutcomeRequestCard(
                                                id = it.requestId,
                                                Sender = it.keyRecipientFullName,
                                                Cab = it.classroomNumber,
                                                Till = it.endOfRequest,
                                                Status = it.status
                                            )
                                        })
                                }.toMutableStateList()
                                println(outcoming.size)
                            }
                        }

                        Column(modifier = Modifier.fillMaxWidth())
                        {
                            TabRow(
                                selectedTabIndex = tabIndex,
                                backgroundColor = darkGray,
                                contentColor = Color.White
                            )
                            {
                                tabs.forEachIndexed { index, title ->
                                    Tab(
                                        text = { Text(title) },
                                        selected = tabIndex == index,
                                        onClick = { tabIndex = index },
                                        selectedContentColor = lightGray,
                                        unselectedContentColor = Color.Gray,
                                        modifier = Modifier.background(
                                            shape = RoundedCornerShape(
                                                bottomStart = 20.dp,
                                                bottomEnd = 20.dp
                                            ), color = darkGray
                                        )
                                    )
                                }
                            }
                            when (tabIndex) {
                                0 -> ShowInComing(incoming)
                                1 -> ShowOutComing(outcoming)
                            }
                        }
                    }
                }
            }
        }
        KeyRequestIncomeActionsMenu(context = LocalContext.current)
        KeyRequestOutcomeActionsMenu(context = LocalContext.current)
    }
}