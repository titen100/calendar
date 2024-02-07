package com.example.calendar


import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calendar.ui.theme.CalendarTheme
import java.time.LocalDate
import java.time.ZoneId


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalendarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF5b3f5b)
                ) {
                    Column (
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        HeadLine()
                        CalendarGrid()
                    }
                }
            }
        }
    }
}

val logic = CalendarLogic()

@RequiresApi(Build.VERSION_CODES.O)
val month = LocalDate.now(ZoneId.systemDefault()).monthValue

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HeadLine() {
    val currentMonth: String = logic.monthName(month)
    Text(text = currentMonth,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFF0F8FF),
        fontFamily = FontFamily.Serif
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarGrid() {
    val daysOfWeek = listOf(
        stringResource(R.string.sunday),
        stringResource(R.string.monday),
        stringResource(R.string.tuesday),
        stringResource(R.string.wednesday),
        stringResource(R.string.thursday),
        stringResource(R.string.friday),
        stringResource(R.string.saturday)
    )
    val currentMonth = logic.getCurrentMonth()
    val daysInMonth = currentMonth.lengthOfMonth()
    val firstDayOfWeek = currentMonth.atDay(1).dayOfWeek.value % 7

    LazyColumn {
        item {
            // Row for the days of the week
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                daysOfWeek.forEach { day ->
                    Text(
                        text = day,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        color = Color(0xFFF0F8FF),
                        fontFamily = FontFamily.Serif
                    )
                }
            }
        }

        items(daysInMonth / 7 + 1) { week ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp), // Set the height of the row
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (day in 0..6) {
                    val date = week * 7 + day - firstDayOfWeek + 1
                    if (date in 1..daysInMonth) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp)
                                .border(1.dp, Color(0xFFF0F8FF)), // Add padding to create space between the dates
                            contentAlignment = Alignment.Center // Center the content in the box
                        ) {
                            Text(
                                text = date.toString(),
                                color = Color(0xFFF0F8FF), // Change the color of the text
                                style = TextStyle(
                                    fontSize = 30.sp, // Adjust the text size
                                    fontFamily = FontFamily.Serif // Change the font
                                ),
                                textAlign = TextAlign.Center // Center the text
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayClickDialog(day: Int) {
    AlertDialog(
        onDismissRequest = { /* Dismiss the dialog */ },
        title = { Text(text = "Days Since January 1st") },
        text = {
            Text(
                text = "Number of days since January 1st: $day",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp)
            )
        },
        confirmButton = {
            Button(
                onClick = { /* Dismiss the dialog */ },
            ) {
                Text("OK")
            }
        }
    )
}


