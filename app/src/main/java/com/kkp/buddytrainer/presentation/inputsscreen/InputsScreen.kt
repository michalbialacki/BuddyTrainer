package com.kkp.buddytrainer.inputsscreen.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.kkp.buddytrainer.presentation.inputsscreen.components.UsersInputs

@Composable
fun InputsScreen(
    navController: NavController,
    buddyId : Long = 404L
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(Modifier.fillMaxSize()) {
            UsersInputs(userId = buddyId)

        }

    }

}