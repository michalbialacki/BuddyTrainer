package com.kkp.buddytrainer.presentation.startscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun StartScreen(
    navController: NavController,
    viewModel: StartScreenViewModel = hiltViewModel()
) {
    Surface() {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
//            TODO("Create Start Screen")
        }

    }
}