package com.kkp.buddytrainer.presentation.startscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kkp.buddytrainer.presentation.startscreen.StartScreenViewModel

@Composable
fun ChooseBuddySurface(
    viewModel: StartScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    Box(modifier = Modifier
        .offset(y=-50.dp)
        .clip(RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.TopCenter){
        Column(
            modifier = Modifier
                .fillMaxSize(0.8f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                BuddySelect(navController = navController)
            }
            Column(
                modifier = Modifier.offset(y=-10.dp).weight(0.5f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
//                AddBudd(navController = navController)
            }
        }
    }
}