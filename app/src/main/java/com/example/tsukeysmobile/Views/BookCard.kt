package com.example.tsukeysmobile.Views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.tsukeysmobile.Navigation.Screen
import com.example.tsukeysmobile.ui.theme.requestRepeatable
import java.time.LocalDate
import java.time.format.DateTimeFormatter




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


    var showError by remember { mutableStateOf(false) }

    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowUp
    }
    var expandedLes by remember { mutableStateOf(false) }
    var selectedItemLes by remember { mutableStateOf("") }
    var textFieldSizeLes by remember { mutableStateOf(Size.Zero) }
    val iconLes = if (expandedLes) {
        Icons.Filled.KeyboardArrowUp
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
                    .padding(start = 12.dp, top = 12.dp, bottom = 12.dp),
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
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },
                    label = { Text(text = "Выберите дату") },
                    trailingIcon = {
                        Icon(icon, "", Modifier.clickable() { expanded = !expanded })
                    },
                    enabled = false,
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = androidx.compose.ui.Modifier
                        .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                )
                {

                }
            }
        }
        Row(
            modifier = Modifier.offset(y = (-100.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .fillMaxHeight(0.1f)
                    .padding(start = 12.dp, top = 12.dp, bottom = 12.dp),
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
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            textFieldSizeLes = coordinates.size.toSize()
                        },
                    label = { Text(text = "Выберите пару") },
                    trailingIcon = {
                        Icon(iconLes, "", Modifier.clickable() { expandedLes = !expandedLes })
                    },
                    enabled = false,

                    )
                DropdownMenu(
                    expanded = expandedLes,
                    onDismissRequest = { expandedLes = false },
                    modifier = androidx.compose.ui.Modifier
                        .width(with(LocalDensity.current) { textFieldSizeLes.width.toDp() })
                )
                {

                }
            }
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