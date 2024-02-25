package com.example.tsukeysmobile.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tsukeysmobile.DefaultText
import com.example.tsukeysmobile.ui.theme.requestSingle
import java.util.Date
import java.util.UUID

@Composable
fun ReservationBoxElement(
    cab: String,
    dateAndLes: String
) {
    val dl = dateAndLes
    DefaultText(text = "кабинет" + " " + cab + " " + dateAndLes, size = 40, modifier = Modifier)
}

@Composable
fun ReservationCard(
    cab: String,
    dateAndLes: String,
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Забронировать данный кабинет?") },
            text = { Text(text = "Если хотите забронировать, нажмите 'Да'") },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Да")
                }
                Button(onClick = { showDialog = false }) {
                    Text("Закрыть")
                }


            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.1f)
            .padding(vertical = 8.dp, horizontal = 10.dp)
            .background(requestSingle, shape = RoundedCornerShape(16.dp))
            .clickable { showDialog = true },
        contentAlignment = Alignment.Center
    ) {
        ReservationBoxElement(cab, dateAndLes)
    }
}