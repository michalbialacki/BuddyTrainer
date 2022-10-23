package com.kkp.buddytrainer.presentation.startscreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun AddBudd(
    navController: NavController
) {
    Row {
        Text(text = "You can add your buddy ")
        Text(text = "manually", modifier = Modifier.clickable {
            navController.navigate("AddBuddyScreen")
        })
    }
    Row {
        Text(text = "or")
    }
    Row {
        Text(text = "you can use his ")
        Text(text = "QR Code")
    }
}