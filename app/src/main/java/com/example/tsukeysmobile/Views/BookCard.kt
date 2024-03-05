package com.example.tsukeysmobile.Views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.tsukeysmobile.Navigation.Screen
import com.example.tsukeysmobile.ui.theme.requestRepeatable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun ChangeTransportedParams(
    text: String?,
    selectedItem: String,
    selectedItemLes: String
): String {
    var returnedParam = if (text == "Завтра") {

        LocalDate.now().plusDays(1).toString() + " - " + selectedItemLes

    } else if (text == "Послезавтра") {

        LocalDate.now().plusDays(2).toString() + " - " + selectedItemLes

    } else if (text == "Сегодня") {

        LocalDate.now().plusDays(0).toString() + " - " + selectedItemLes

    } else {

        selectedItem + " - " + selectedItemLes

    }
    return returnedParam
}

@Composable
fun errrorOfChoise(text: String): TextFieldColors {
    if (text.isNotEmpty()) {
        return OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.Black,
            unfocusedContainerColor = Color.Black,
            disabledContainerColor = Color.Black,
            focusedLabelColor = Color.Black,
            disabledTextColor = Color.White,
            disabledBorderColor = Color.White,
            disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledTrailingIconColor = Color.White,
            disabledLabelColor = Color.White,
            disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledPrefixColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    } else {
        return OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.Black,
            unfocusedContainerColor = Color.Black,
            disabledContainerColor = Color.Black,
            focusedLabelColor = Color.Black,
            disabledTextColor = Color.White,
            disabledBorderColor = Color.Red,
            disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledTrailingIconColor = Color.White,
            disabledLabelColor = Color.White,
            disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledPrefixColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun DropDownText(
    text: String
) {
    Text(
        text = text,
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp)
    )
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookCard(navController: NavController) {
    val list = listOf(
        "1 Пара - 8:45-10:20",
        "2 Пара - 10:35-12:10",
        "3 Пара - 12:25-14:00",
        "4 Пара - 14:45-16:20",
        "5 Пара - 16:35-18:10",
        "6 Пара - 18:25-20:00"
    )
    val currentDate = LocalDate.now()
    val dateList = (1 until 9).map { currentDate.plusDays(it.toLong()) }
    val formattedDateList = dateList.mapIndexed { index, date ->
        when (index) {
            0 -> "Сегодня"
            1 -> "Завтра"
            2 -> "Послезавтра"
            else -> date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        }
    }

    var showError by remember { mutableStateOf(false) }

    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowDown
    } else {
        Icons.Filled.KeyboardArrowUp
    }
    var expandedLes by remember { mutableStateOf(false) }
    var selectedItemLes by remember { mutableStateOf("") }
    var textFieldSizeLes by remember { mutableStateOf(Size.Zero) }
    val iconLes = if (expandedLes) {
        Icons.Filled.KeyboardArrowDown
    } else {
        Icons.Filled.KeyboardArrowUp
    }



    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Row(
            modifier = Modifier.offset(y = (-100.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .fillMaxHeight(0.1f)
                    .padding(start = 8.dp, top = 12.dp, bottom = 5.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            )
            {
                DropDownText(text = "Дата")
            }

            Column(modifier = Modifier.padding(end = 12.dp, top = 12.dp, bottom = 12.dp)) {

                OutlinedTextField(
                    value = selectedItem,
                    onValueChange = { selectedItem = it },
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },
                    textStyle = TextStyle(color = Color.White),
                    shape = RoundedCornerShape(16.dp),
                    label = {
                        Text(
                            text = "Выберите дату",
                            color = if (expanded == true) {
                                if (selectedItem != "") {
                                    Color.Black
                                } else {
                                    Color.White
                                }
                            } else {
                                if (selectedItem != "") {
                                    Color.Black
                                } else {
                                    Color.White
                                }
                            }
                        )
                    },
                    trailingIcon = {
                        Icon(icon, "", Modifier.clickable() { expanded = !expanded })
                    },
                    enabled = false,
                    colors = errrorOfChoise(selectedItem)
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = androidx.compose.ui.Modifier
                        .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                        .background(Color.Black)
                )
                {
                    formattedDateList.forEach { label ->
                        DropdownMenuItem(
                            text = {
                                Text(text = label, color = Color.White)
                            }, onClick = {
                                selectedItem = label
                                expanded = false
                            })
                    }
                }
            }
        }
        Row(
            modifier = Modifier.offset(y = (-100.dp)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .fillMaxHeight(0.1f)
                    .padding(start = 6.dp, top = 6.dp, bottom = (2.5).dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            )
            {
                DropDownText(text = "Время")
            }

            Column(modifier = Modifier.padding(end = 12.dp, top = 12.dp, bottom = 12.dp)) {
                OutlinedTextField(
                    value = selectedItemLes,
                    onValueChange = { selectedItemLes = it },
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .onGloballyPositioned { coordinates ->
                            textFieldSizeLes = coordinates.size.toSize()
                        },
                    shape = RoundedCornerShape(16.dp),
                    textStyle = TextStyle(color = Color.White),
                    label = {
                        Text(
                            text = "Выберите пару",
                            color = if (expanded == true) {
                                if (selectedItemLes != "") {
                                    Color.Black
                                } else {
                                    Color.White
                                }
                            } else {
                                if (selectedItemLes != "") {
                                    Color.Black
                                } else {
                                    Color.White
                                }
                            }
                        )
                    },

                    trailingIcon = {
                        Icon(iconLes, "", Modifier.clickable() { expandedLes = !expandedLes })
                    },
                    enabled = false,
                    colors = errrorOfChoise(selectedItemLes)

                )
                DropdownMenu(
                    expanded = expandedLes,
                    onDismissRequest = { expandedLes = false },
                    modifier = androidx.compose.ui.Modifier
                        .width(with(LocalDensity.current) { textFieldSizeLes.width.toDp() })
                        .background(Color.Black)
                )
                {
                    list.forEach { label ->
                        DropdownMenuItem(
                            text = {
                                Text(text = label, color = Color.White)
                            }, onClick = {
                                selectedItemLes = label
                                expandedLes = false
                            })
                    }
                }
            }
        }
        Divider(modifier = Modifier
            .fillMaxWidth(0.9f)
            .absoluteOffset(y = (-100).dp), color = Color.Black, thickness = 4.dp)
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(requestRepeatable, shape = RoundedCornerShape(16.dp))
                .clickable {
                    if (selectedItem.isNotEmpty() && selectedItemLes.isNotEmpty()) {
                        if (selectedItem == "Завтра") {
                            navController.navigate(
                                Screen.CabScreen.withArgs(
                                    ChangeTransportedParams("Завтра", selectedItem, selectedItemLes)
                                )
                            )
                        } else if (selectedItem == "Послезавтра") {
                            navController.navigate(
                                Screen.CabScreen.withArgs(
                                    ChangeTransportedParams(
                                        "Послезавтра",
                                        selectedItem,
                                        selectedItemLes
                                    )
                                )
                            )
                        } else if (selectedItem == "Сегодня") {
                            navController.navigate(
                                Screen.CabScreen.withArgs(
                                    ChangeTransportedParams(
                                        "Сегодня",
                                        selectedItem,
                                        selectedItemLes
                                    )
                                )
                            )
                        } else {
                            navController.navigate(
                                Screen.CabScreen.withArgs(
                                    ChangeTransportedParams(
                                        null,
                                        selectedItem,
                                        selectedItemLes
                                    )
                                )
                            )
                        }
                    } else {
                        showError = true
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Кабинет",
                style = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Bold),
                color = Color.White
            )
        }
        if (showError) {
            Text(
                text = "Выберите Дату и Пару",
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp),
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
        }


    }
}