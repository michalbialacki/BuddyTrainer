package com.kkp.buddytrainer.presentation.startscreen.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun StartScreenSurface(
    navController: NavController
) {
    var isVisible by remember{
        mutableStateOf(false)
    }

    LaunchedEffect(true){
        isVisible = true
    }


    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(0.8f)
                .background(Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            TopBar(navController = navController)
            AnimatedVisibility(
                visible = isVisible,
                enter = slideInHorizontally() + fadeIn(),
                exit = slideOutHorizontally() + fadeOut()
            ) {
                ChooseBuddySurface(navController = navController)
            }
        }

    }
}