package com.example.tsukeysmobile.Views

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.example.tsukeysmobile.AUTHORIZE_TOKEN
import com.example.tsukeysmobile.DefaultText
import com.example.tsukeysmobile.R
import com.example.tsukeysmobile.Requests.Error.ErrorData
import com.example.tsukeysmobile.Requests.KeyRequests.CreateRequestBody
import com.example.tsukeysmobile.Screens.Request
import com.example.tsukeysmobile.Screens.requestService
import com.example.tsukeysmobile.ui.theme.*
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.util.*
import java.util.EnumSet.range
import kotlin.math.exp


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFieldSample(
    placeholder: String = "",
    value: String = "",
    enabled: Boolean = true,
    modifier: Modifier,
    onValueChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current!!
    var text by rememberSaveable {
        mutableStateOf("")
    }
    TextField(
        value = text,
        placeholder = {DefaultText(text = placeholder, size = 20, modifier = Modifier, color = Color.Gray)},
        enabled = enabled,
        onValueChange = { newText ->
            run {
                onValueChange(newText)
                text = newText
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController.hide(); focusManager.clearFocus() }),
        singleLine = true,
        modifier = modifier
            .background(Color.Transparent)
            .border(
                width = 2.dp, color = Color.Black
            ),
        textStyle = TextStyle(
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.interblack)),
        ),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color.White,
        ),
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFieldChooseSample(
    value: String = "",
    enabled: Boolean = true,
    modifier: Modifier,
    onValueChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current!!
    var text by rememberSaveable {
        mutableStateOf("")
    }
    TextField(
        value = value,
        enabled = enabled,
        onValueChange = { newText ->
            run {
                onValueChange(newText)
                text = newText
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController.hide(); focusManager.clearFocus() }),
        singleLine = true,
        modifier = modifier
            .background(Color.Transparent)
            .border(
                width = 2.dp, color = Color.Black
            ),
        textStyle = TextStyle(
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.interblack)),
        ),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color.White,
        ),
    )
}


@SuppressLint("UnrememberedMutableState")
@Composable
fun KeysMenuCreateKey()
{
    val openDialog = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .wrapContentHeight()
            .background(color = Color.Gray, shape = RoundedCornerShape(10.dp))
            .clickable { openDialog.value = true },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Image(painter = painterResource(id = R.drawable.img), contentDescription = null, modifier = Modifier.size(50.dp))
        DefaultText(text = "Передать\nключ", size = 20, modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp), color = Color.White)

    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    )
    {

        if (openDialog.value)
        {
            Box(modifier = Modifier
                .background(color = gray, shape = RoundedCornerShape(10.dp))
                .fillMaxSize()
                .border(1.dp, Color.Black, shape = RoundedCornerShape(10.dp)))
            {
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally)
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
                        Box(
                            modifier = Modifier
                                .clickable { Toast.makeText(context, "Создание заявки на передачу ключа", Toast.LENGTH_SHORT).show() }
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
                            .clickable { openDialog.value = !openDialog.value }
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
                    DefaultText(text = "кому:", size = 20, modifier = Modifier, color = Color.White)
                    val message = remember{mutableStateOf("")}
                    TextFieldSample(modifier = Modifier, onValueChange = {message.value = it}, placeholder = "введите email пользователя")

                    DefaultText(text = "ключ:", size = 20, modifier = Modifier, color = Color.White)
                    var expanded by remember { mutableStateOf(false) }
                    val items = listOf("227", "228", "229", "229", "229", "229")
                    val cab = remember{mutableStateOf("")}

                    Box(
                        modifier = Modifier
                            .background(color = Color.Transparent)
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        contentAlignment = Alignment.Center
                    )
                    {
                        DropdownMenu(
                            modifier = Modifier
                                .wrapContentSize()
                                .background(color = Color.Black),
                            offset = DpOffset(x = 50.dp, y = 0.dp),
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                        )
                        {
                            for (i in items) {
                                DropdownMenuItem(
                                    onClick = {
                                        expanded = false
                                        cab.value = i
                                    }
                                )
                                {
                                    DefaultText(
                                        text = i,
                                        size = 20,
                                        modifier = Modifier,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                        TextFieldChooseSample(
                            value = cab.value,
                            onValueChange = {},
                            enabled = false,
                            modifier = Modifier.clickable { expanded = true })
                    }
                    val coroutineScope = rememberCoroutineScope()

                    Button(
                        colors = ButtonDefaults.buttonColors(darkGray),
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .wrapContentHeight()
                            .padding(vertical = 10.dp),
                        onClick = {
                            coroutineScope.launch {
                                val response = requestService.createKeyRequest(AUTHORIZE_TOKEN, body = CreateRequestBody(keyRecipient = message.value, classroomNumber = cab.value)).awaitResponse()

                                if (response.isSuccessful)
                                {

                                }
                                else
                                {
                                    val errorResponse = Gson().fromJson(response.errorBody()?.string(), ErrorData::class.java)
                                    Toast.makeText(context, errorResponse.Message, Toast.LENGTH_SHORT).show()
                                }
                            }

                        }
                    )
                    {
                        DefaultText(text = "Передать ключ", size = 25, modifier = Modifier.padding(horizontal = 20.dp))
                    }
                }
            }
        }
    }
}