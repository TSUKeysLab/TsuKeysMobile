package com.example.tsukeysmobile.Views

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.util.Log
import android.util.Patterns
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.tsukeysmobile.DefaultText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RegisterElementOutlined(fieldText: String, type: String): String {

    if (type == "Date") {
        val year: Int
        val month: Int
        val day: Int

        val calendar = Calendar.getInstance()
        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)
        calendar.time = Date()

        var date = remember { mutableStateOf("") }
        val datePickerDialog = DatePickerDialog(
            LocalContext.current,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->

                val inputFormat = SimpleDateFormat("yyyy-M-d", Locale.getDefault())
                val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val dateParse = inputFormat.parse("$year-$month-$dayOfMonth")
                date.value = outputFormat.format(dateParse!!)
            }, year, month, day
        )


        OutlinedTextField(
            value = date.value,
            onValueChange = {
                date.value = it
            },
            modifier = Modifier
                .fillMaxWidth(0.9f),
            shape = RoundedCornerShape(8.dp),
            textStyle = TextStyle(color = Color.Black),
            label = { Text(fieldText) },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    Modifier.clickable() { datePickerDialog.show() }
                )
            },
            enabled = false,
            visualTransformation = VisualTransformation.None,
            colors = error(type, date.value),
            maxLines = 1,
        )
        error(type, date.value)
        return date.value

    } else if (type == "Gender") {
        val listOfGenders = listOf("Male", "Female")
        var expanded by remember { mutableStateOf(false) }
        var selectedItem by remember { mutableStateOf("") }
        var textFieldSize by remember { mutableStateOf(Size.Zero) }
        val icon = if (expanded) {
            Icons.Filled.KeyboardArrowUp
        } else {
            Icons.Filled.KeyboardArrowDown
        }
        Column {
            OutlinedTextField(
                value = selectedItem,
                onValueChange = {
                    selectedItem = it
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .onGloballyPositioned { coordinates ->
                        textFieldSize = coordinates.size.toSize()
                    },
                label = {
                    Text(
                        text = "Выберите пол", color =
                        if (expanded == true && selectedItem != "") {
                            Color.White
                        } else {
                            Color.DarkGray
                        }

                    )
                },
                trailingIcon = {
                    Icon(icon, "", Modifier.clickable() { expanded = !expanded })
                },
                enabled = false,
                colors = error(type, selectedItem),
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
            )
            {
                listOfGenders.forEach { label ->
                    DropdownMenuItem(
                        text = {
                            Text(text = label, color = Color.Black)
                        }, onClick = {
                            selectedItem = label
                            expanded = false
                        })
                }
            }
        }

        return selectedItem

    } else {
        var text by remember { mutableStateOf(TextFieldValue("")) }
        var maxChar = 15
        if(type == "email"){
            maxChar = 20
        }
        OutlinedTextField(
            value = text,
            onValueChange = {
                if (it.text.length <= maxChar) {
                    text = it
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.9f),
            shape = RoundedCornerShape(8.dp),
            textStyle = TextStyle(color = Color.Black),
            label = { Text(fieldText) },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            visualTransformation = VisualTransformation.None,
            colors = error(type, text.text),
            maxLines = 1,
        )
        return text.text
    }
}

@Composable
fun error(type: String, text: String): TextFieldColors {

    if (type == "email") {
        if (Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
            return OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedLabelColor = Color.White,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            )
        } else {
            return OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedLabelColor = Color.White,
                focusedBorderColor = Color.Red,
                unfocusedBorderColor = Color.Red
            )
        }
    } else if (type == "password") {
        val passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=.*[a-zA-Z]).{8,}\$"
        if (text.matches(passwordRegex.toRegex())) {
            return OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedLabelColor = Color.White,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            )
        } else {
            return OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedLabelColor = Color.White,
                focusedBorderColor = Color.Red,
                unfocusedBorderColor = Color.Red
            )
        }
    } else if (type == "Date" || type == "Gender") {
        if (text != "") {
            return OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedLabelColor = Color.White,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledBorderColor = Color.Black,
                disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onSurface,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledPrefixColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            return OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedLabelColor = Color.White,
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledBorderColor = Color.Red,
                disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onSurface,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledPrefixColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    } else {
        if (text.isNotEmpty() && text[0].isUpperCase()) {
            return OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedLabelColor = Color.White,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            )
        } else {
            return OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedLabelColor = Color.White,
                focusedBorderColor = Color.Red,
                unfocusedBorderColor = Color.Red
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)

@Composable
fun RegisterElement(topLabel: String, text: String, type: String): String {
    Column(modifier = Modifier.fillMaxWidth(0.9f), horizontalAlignment = Alignment.Start) {
        DefaultText(text = topLabel, size = 20, modifier = Modifier.offset(y = 12.dp))
        Spacer(modifier = Modifier.height(12.dp))

    }
    return (RegisterElementOutlined(text, type))
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun RegistrationCard(): List<String> {
    var name = ""
    var surname = ""
    var bd = ""
    var gender = ""
    var email = ""
    var password = ""

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black, RoundedCornerShape(16.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        name = RegisterElement("имя", "Введите имя", "name")
        surname = RegisterElement("фамилия", "Введите фамилию", "surname")
        bd = RegisterElement("дата рождения", "Выберите дату рождения", "Date")
        gender = RegisterElement("пол", "Выберите пол", "Gender")
        email = RegisterElement("email", "Введите почту", "email")
        password = RegisterElement("пароль", "Введите пароль", "password")

    }
    return listOf(name, surname, bd, gender, email, password)
}