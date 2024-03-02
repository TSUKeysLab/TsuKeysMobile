package com.example.tsukeysmobile.Views

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tsukeysmobile.DefaultText
import com.example.tsukeysmobile.RequestCard
import com.example.tsukeysmobile.Screens.Request
import com.example.tsukeysmobile.Screens.requests
import com.example.tsukeysmobile.ui.theme.*
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
fun RequestActionsMenu()
{
    Box(
        modifier = Modifier
            .wrapContentSize(),
        contentAlignment = Alignment.Center
    )
    {
        if (openRequestActionsMenu)
        {
            Box(modifier = Modifier
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
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
                            .background(color = Color.Gray, shape = RoundedCornerShape(50))
                            .size(30.dp), contentAlignment = Alignment.Center)
                        {
                            DefaultText(text = "?", size = 20, modifier = Modifier, color = Color.White)
                        }
                        Box(modifier = Modifier
                            .clickable { openRequestActionsMenu = !openRequestActionsMenu }
                            .background(color = Color.Gray)
                            .size(30.dp), contentAlignment = Alignment.Center)
                        {
                            DefaultText(text = "X", size = 20, modifier = Modifier, color = Color.White)
                        }
                    }
                    request.value.element()
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .wrapContentHeight(),
                        horizontalArrangement = Arrangement.SpaceBetween)
                    {
                        Button(modifier = Modifier
                            .border(width = 3.dp, color = Color.Black)
                            .weight(1f)
                            .wrapContentSize(), onClick = {
                                val index = requests.indexOf(request.value)
                                requests[index].visibleState.targetState = false
                                openRequestActionsMenu = false
                        },
                            colors = ButtonDefaults.buttonColors(Color.Red))
                        {
                            DefaultText(
                                text = "Удалить заявку",
                                size = 18,
                                modifier = Modifier,
                                color = Color.Black
                            )
                        }
                        Button(modifier = Modifier
                            .background(color = Color.Green)
                            .border(width = 3.dp, color = Color.Black)
                            .weight(1f)
                            .wrapContentSize(), onClick = {
                                val index = requests.indexOf(request.value)
                                requests[index].visibleState.targetState = false
                                openRequestActionsMenu = false
                        },
                            colors = ButtonDefaults.buttonColors(Color.Green))
                        {
                            DefaultText(
                                text = "Получил ключ",
                                size = 18,
                                modifier = Modifier,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}