package com.example.tsukeysmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tsukeysmobile.Screens.RequestsScreen
import com.example.tsukeysmobile.Screens.Request
import com.example.tsukeysmobile.ui.theme.TsuKeysMobileTheme
import com.example.tsukeysmobile.ui.theme.requestRepeatable
import com.example.tsukeysmobile.ui.theme.requestSingle
import java.util.*

val requests : MutableList<Request> = mutableStateListOf(Request(UUID.randomUUID()) {
    Divider(modifier = Modifier.fillMaxWidth(0.9f).offset(x = 20.dp).padding(vertical = 50.dp), color = Color.Black, thickness = 6.dp)
})

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TsuKeysMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    val a = UUID.randomUUID()
                    requests.add(0, Request(a) {
                        RequestCard(
                            id = a,
                            date = "12.02",
                            cab = "227",
                            time = "12:25-14:00",
                            type = requestSingle
                        )
                    })
                    val b = UUID.randomUUID()
                    requests.add(0, Request(b) {
                        RequestCard(
                            id = b,
                            date = "14.02",
                            cab = "228",
                            time = "18:25-20:00",
                            type = requestSingle
                        )
                    })
                    val c = UUID.randomUUID()
                    requests.add(Request(c) {
                        RequestCard(
                            id = c,
                            date = "вт",
                            cab = "228",
                            time = "18:25-20:00",
                            type = requestRepeatable
                        )
                    })
                    val d = UUID.randomUUID()
                    requests.add(Request(d) {
                        RequestCard(
                            id = d,
                            date = "ср",
                            cab = "14",
                            time = "16:35-18:10",
                            type = requestRepeatable
                        )
                    })
                    val e = UUID.randomUUID()
                    requests.add(Request(e) {
                        RequestCard(
                            id = e,
                            date = "ср",
                            cab = "14",
                            time = "16:35-18:10",
                            type = requestRepeatable
                        )
                    })

                    RequestsScreen()
                }
            }
        }
    }
}