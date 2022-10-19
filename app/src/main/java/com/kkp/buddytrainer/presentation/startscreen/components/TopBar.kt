package com.kkp.buddytrainer.presentation.startscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopBar() {
    val soloSwitch = remember {
        mutableStateOf(false)
    }
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.DarkGray),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart){
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Inputs")
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            contentAlignment = Alignment.CenterEnd
        ){
            Switch(checked = soloSwitch.value, onCheckedChange = {soloSwitch.value = it} )
        }

    }

}