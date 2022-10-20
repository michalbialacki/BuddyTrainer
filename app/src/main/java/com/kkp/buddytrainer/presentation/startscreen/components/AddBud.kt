package com.kkp.buddytrainer.presentation.startscreen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun AddBudd() {
    Row {
        Text(text = "You can add your buddy ")
        Text(text = "manually")
    }
    Row {
        Text(text = "or")
    }
    Row {
        Text(text = "you can use his ")
        Text(text = "QR Code")
    }
}