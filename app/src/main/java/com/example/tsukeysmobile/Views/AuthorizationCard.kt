@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.example.tsukeysmobile.Views


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tsukeysmobile.DefaultText
import com.example.tsukeysmobile.Navigation.Screen
import com.example.tsukeysmobile.R


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AuthorizationElementOutlined(fieldText: String, type: String): String {

    var text by remember { mutableStateOf(TextFieldValue("")) }
    var maxChar = 15

    if (type == "password") {
        var passwordVisibility by remember { mutableStateOf(false) }
        val icon = if (passwordVisibility) {
            painterResource(id = com.google.android.material.R.drawable.design_ic_visibility)
        } else {
            painterResource(id = com.google.android.material.R.drawable.design_ic_visibility_off)
        }

        OutlinedTextField(
            value = text,
            onValueChange = {
                if (it.text.length <= maxChar) {
                    text = it
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.8f),
            shape = RoundedCornerShape(8.dp),
            textStyle = TextStyle(color = Color.Black),
            label = { Text(fieldText) },
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(painter = icon, contentDescription = "")
                }
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            visualTransformation = if (passwordVisibility) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedLabelColor = Color.White,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            ),
            maxLines = 1,
        )
    } else {
        maxChar = 20
        OutlinedTextField(
            value = text,
            onValueChange = {
                if (it.text.length <= maxChar) {
                    text = it
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.8f),
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
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedLabelColor = Color.White,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            ),
            maxLines = 1,
        )
    }

    return text.text
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AuthorizationElement(topLabel: String, text: String, type: String): String {
    Column(modifier = Modifier.fillMaxWidth(0.9f), horizontalAlignment = Alignment.Start) {
        DefaultText(text = topLabel, size = 20, modifier = Modifier.offset(y = 12.dp, x = 20.dp))
        Spacer(modifier = Modifier.height(12.dp))

    }
    return (AuthorizationElementOutlined(text, type))
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AuthorizationCard(navController: NavController): List<String> {

    var email = ""
    var password = ""

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Column(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .background(Color.Black, RoundedCornerShape(16.dp))
                .height(150.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            email = AuthorizationElement("email", "Введите почту", "email")
        }


        Column(
            modifier = Modifier
                .background(Color.Black, RoundedCornerShape(16.dp))
                .height(150.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            password = AuthorizationElement("пароль", "Введите пароль", "password")
        }
        Spacer(modifier = Modifier.height(20.dp))
        ClickableText(
            text = AnnotatedString("Зарегистрироваться"),
            onClick = { navController.navigate(Screen.RegScreen.withArgs()) },
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(3.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth(0.5f), color = Color.Black, thickness = 4.dp
        )
    }
    return listOf(email, password)
}