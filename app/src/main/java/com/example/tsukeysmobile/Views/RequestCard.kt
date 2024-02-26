package com.example.tsukeysmobile


import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tsukeysmobile.ui.theme.requestRepeatable
import java.util.*


@Composable
fun DefaultText(
    text: String,
    size: Int,
    color: Color = Color.White,
    modifier: Modifier
)
{
    Text(modifier = modifier, overflow = TextOverflow.Ellipsis, text = text, fontSize = size.sp, fontFamily = FontFamily(Font(R.font.interblack)), color = color, style = TextStyle(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false,
            ),
        )
    )
}

@Composable
fun TimePart(
    start: String,
    end: String
)
{
    DefaultText(text = start, size = 18, modifier = Modifier)
    DefaultText(text = "-", size = 18, modifier = Modifier)
    DefaultText(text = end, size = 18, modifier = Modifier)
}

@Composable
fun CabPart(
    cab: String
)
{
    DefaultText(text = cab, size = 40, modifier = Modifier)
    DefaultText(text = "кабинет", size = 20, modifier = Modifier)
}

@Composable
fun DatePart(
    day: String,
    month: String
)
{
    DefaultText(text = day, size = 40, modifier = Modifier.absoluteOffset((-8).dp, 3.dp))
    Divider(modifier = Modifier.width(100.dp).absoluteOffset(y = (-5).dp), color = Color.White, thickness = 7.dp)
    DefaultText(text = month, size = 40, modifier = Modifier.absoluteOffset(8.dp, (-13).dp))
}

@Composable
fun WeekDayPart(
    weekDay: String
)
{
    DefaultText(text = weekDay, size = 40, modifier = Modifier)
}


@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestCard(
    id: UUID,
    date: String,
    cab: String,
    time: String,
    type: Color
) {
    var offsetX by remember { mutableStateOf(0f) }

    Card(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth().height(90.dp)
            .offset { IntOffset(x = offsetX.toInt(), y = 0) }
            .pointerInput(Unit)
            {
                detectDragGesturesAfterLongPress(
                    onDragStart =
                    {

                    },
                    onDragEnd =
                    {
                        if (offsetX >- 400) offsetX = 0f
                        else
                        {
                            val index = requests.indexOf(requests.find { it.id == id })
                            println(index)
                            requests[index].visibleState.targetState = false
                        }
                    },
                    onDragCancel =
                    {
                    }
                )
                { change, dragAmount ->
                    change.consume()
                    if (dragAmount.x <= 0 || offsetX <= 0) offsetX += dragAmount.x
                }
            },
        colors = CardDefaults.cardColors(
            containerColor = type,
        ),
        shape = RoundedCornerShape(percent = 35),


    )
    {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        )
        {
            Column(
                modifier = Modifier.weight(0.20f),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                if (type == requestRepeatable) WeekDayPart(date) else DatePart(date.split('.')[0], date.split('.')[1])
            }

            Column(
                modifier = Modifier.weight(0.58f),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                CabPart(cab)
            }

            Column(
                modifier = Modifier.weight(0.22f).padding(vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                TimePart(time.split('-')[0], time.split('-')[1])
            }
        }
    }
}