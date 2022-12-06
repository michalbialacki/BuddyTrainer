package com.kkp.buddytrainer.presentation.inputsscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kkp.buddytrainer.ui.theme.Teal200

@Composable
fun ExerciseNamesColumn() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center)
    {
        Spacer(modifier = Modifier.size(40.dp))
        Column(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .background(Teal200)
                .padding(
                    start = 12.dp,
                ),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Bench\npress PR", textAlign = TextAlign.Center, fontSize = 12.sp)
            Text(text = "Squat\nPR", textAlign = TextAlign.Center, fontSize = 12.sp)
            Text(text = "Deadlift\nPR", textAlign = TextAlign.Center, fontSize = 12.sp)

        }
    }
}