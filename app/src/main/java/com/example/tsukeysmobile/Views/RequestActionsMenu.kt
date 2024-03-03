package com.example.tsukeysmobile.Views

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import com.example.tsukeysmobile.DefaultText
import com.example.tsukeysmobile.RequestCard
import com.example.tsukeysmobile.Requests.AUTHORIZE_TOKEN
import com.example.tsukeysmobile.Requests.Error.ErrorData
import com.example.tsukeysmobile.Screens.Request
import com.example.tsukeysmobile.Screens.requestService
import com.example.tsukeysmobile.Screens.requests
import com.example.tsukeysmobile.ui.theme.*
import com.google.gson.Gson
import kotlinx.coroutines.launch

import retrofit2.awaitResponse
import java.util.*


var openRequestActionsMenu by mutableStateOf(false)

@RequiresApi(Build.VERSION_CODES.O)
var request: MutableState<Request> = mutableStateOf(Request(id = "") {
    RequestCard(
        "",
        "",
        "",
        "",
        "",
        requestSingle
    )
})

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnrememberedMutableState")
@Composable
fun RequestActionsMenu(context: Context)
{
    val popupAlpha by animateFloatAsState(
        targetValue = if (openRequestActionsMenu) 1f else 0f,
        animationSpec = tween(durationMillis = 300)
    )
    Box(
        modifier = Modifier
            .fillMaxSize().padding(10.dp),
        contentAlignment = Alignment.Center
    )
    {
        if (openRequestActionsMenu)
        {
            Box(modifier = Modifier.alpha(popupAlpha)
                .background(color = darkGray, shape = RoundedCornerShape(10.dp))
                .wrapContentSize()
                .border(1.dp, Color.Black, shape = RoundedCornerShape(10.dp)))
            {
                Column(modifier = Modifier.wrapContentSize())
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
                            .clickable { Toast.makeText(context, "Действия с заявкой на бронь кабинета", Toast.LENGTH_SHORT).show() }
                            .background(color = darkGray, shape = RoundedCornerShape(50))
                            .size(30.dp), contentAlignment = Alignment.Center)
                        {
                            DefaultText(text = "?", size = 20, modifier = Modifier, color = Color.White)
                        }
                        Box(modifier = Modifier
                            .clickable { openRequestActionsMenu = !openRequestActionsMenu }
                            .background(color = darkGray)
                            .size(30.dp), contentAlignment = Alignment.Center)
                        {
                            DefaultText(text = "X", size = 20, modifier = Modifier, color = Color.White)
                        }
                    }
                    Box(modifier = Modifier.padding(10.dp))
                    {
                        request.value.element()
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        val coroutineScope = rememberCoroutineScope()

                        Button(modifier = Modifier
                            .border(width = 3.dp, color = Color.Black, shape = RoundedCornerShape(topStart = 50.dp, bottomStart = 50.dp))
                            .weight(1f)
                            .wrapContentSize(), onClick = {
                                coroutineScope.launch {

                                    val response = requestService.deleteRequest(AUTHORIZE_TOKEN, request.value.id).awaitResponse()

                                    if (response.isSuccessful)
                                    {
                                        val index = requests.indexOf(request.value)
                                        requests[index].visibleState.targetState = false
                                        openRequestActionsMenu = false
                                    }
                                    else
                                    {
                                        val errorResponse = Gson().fromJson(response.errorBody()?.string(), ErrorData::class.java)
                                        Toast.makeText(context, errorResponse.Message, Toast.LENGTH_SHORT).show()
                                    }
                                }
                            },
                            shape = RoundedCornerShape(topStart = 50.dp, bottomStart = 50.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFFEF5350)))
                        {
                            DefaultText(
                                text = "Удалить заявку",
                                size = 20,
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }
                        Button(modifier = Modifier
                            .border(width = 3.dp, color = Color.Black, shape = RoundedCornerShape(topEnd = 50.dp, bottomEnd = 50.dp))
                            .weight(1f)
                            .wrapContentSize(), onClick = {
                                coroutineScope.launch {
                                    val response = requestService.confirmKeyRequest(AUTHORIZE_TOKEN, request.value.id).awaitResponse()

                                    if (response.isSuccessful)
                                    {
                                        val index = requests.indexOf(request.value)
                                        requests[index].visibleState.targetState = false
                                        openRequestActionsMenu = false
                                    }
                                    else
                                    {
                                        val errorResponse = Gson().fromJson(response.errorBody()?.string(), ErrorData::class.java)
                                        Toast.makeText(context, errorResponse.Message, Toast.LENGTH_SHORT).show()
                                    }
                                }
                            },
                            shape = RoundedCornerShape(topEnd = 50.dp, bottomEnd = 50.dp),
                            colors = ButtonDefaults.buttonColors(darkGreen))
                        {
                            DefaultText(
                                text = "Получил\nключ",
                                size = 20,
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }
                    }
                }
            }
        }
    }
}