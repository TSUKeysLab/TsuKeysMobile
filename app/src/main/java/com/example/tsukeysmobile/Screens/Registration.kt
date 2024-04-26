package com.example.tsukeysmobile.Screens


import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.tsukeysmobile.DefaultText
import com.example.tsukeysmobile.Navigation.Screen
import com.example.tsukeysmobile.R
import com.example.tsukeysmobile.Requests.Error.ErrorData
import com.example.tsukeysmobile.Requests.RequestsFunctions
import com.example.tsukeysmobile.Views.BookCard
import com.example.tsukeysmobile.Views.RegistrationCard
import com.example.tsukeysmobile.ui.theme.backgroundCol1
import com.example.tsukeysmobile.ui.theme.backgroundCol2
import com.google.gson.Gson

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RegistrationScreen(navController: NavController) {
    var elements: List<String>
    var ready by remember { mutableStateOf(false) }
    val context = LocalContext.current



    Column(
        modifier = Modifier.fillMaxSize()
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 80.dp)
                .background(color = Color.Black)
                .zIndex(1f)
        )
        {
            DefaultText(
                text = "регистрация",
                size = 55,
                modifier = Modifier.offset(x = 20.dp, y = 12.dp)
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            backgroundCol1,
                            backgroundCol2,
                            backgroundCol2
                        )
                    )
                )
                .padding(horizontal = 20.dp),
        )
        {
            elements = RegistrationCard(navController)
            if (ready == true) {
                val req = RequestsFunctions()
                if (elements[2] == "") {
                    Toast.makeText(context, "Выберите дату рождения", Toast.LENGTH_SHORT).show()
                    ready = false
                } else if (elements[3] == "") {
                    Toast.makeText(context, "Выберите пол", Toast.LENGTH_SHORT).show()
                    ready = false
                } else {
                    LaunchedEffect(Unit) {
                        val resp = req.postRegistration(
                            name = elements[0],
                            surname = elements[1],
                            bd = elements[2],
                            gender = elements[3],
                            email = elements[4],
                            password = elements[5]
                        )
                        ready = false
                        if (resp.code() == 200) {
                            navController.navigate(Screen.RequestsScreen.withArgs())
                        } else {
                            val errorResponse =
                                Gson().fromJson(resp.errorBody()?.string(), ErrorData::class.java)
                            Toast.makeText(context, errorResponse.Message, Toast.LENGTH_SHORT)
                                .show()
                        }

                    }
                }
            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 80.dp)
                .background(color = Color.Black),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Button(
                onClick = {
                    ready = true
                },
                Modifier
                    .fillMaxWidth(0.4f)
                    .background(Color.Cyan, shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            ) {
                DefaultText(
                    text = "Готово",
                    size = 20,
                    color = Color.Black,
                    modifier = Modifier
                )
            }
        }
    }

}