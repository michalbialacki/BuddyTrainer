package com.kkp.buddytrainer.presentation.startscreen.components

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kkp.buddytrainer.R
import com.kkp.buddytrainer.presentation.startscreen.StartScreenViewModel

@Composable
fun IconMenu(
    navController: NavController,
    viewModel : StartScreenViewModel = hiltViewModel()
) {
    var soloSwitch by remember {
        mutableStateOf(viewModel.soloTrainingSwitch)
    }
    val trainingBuddyId by remember{
        mutableStateOf(viewModel.selectedBuddy)
    }
    var isVisible by remember {
        mutableStateOf(false)
    }

    Box {
        Row {
            Column {
                IconItem(painter = painterResource(id = R.drawable.menu_hamburger)) {
                    isVisible = !isVisible

                } // Show/Hide menu action button
                AnimatedVisibility(
                    visible = isVisible,
                    enter = slideInVertically(tween(500)) + fadeIn(),
                    exit = slideOutVertically(tween(500)) + fadeOut()
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    IconItem(painter = painterResource(id = R.drawable.add_buddy)) {
                        navController.navigate("AddBuddyScreen")
                    } // DOWN; Manual input
                }
            }
            Column {
                AnimatedVisibility(
                    visible = isVisible,
                    enter = slideInHorizontally(tween(500)) + fadeIn(),
                    exit = slideOutHorizontally(tween(500)) + fadeOut()
                ) {
                    Spacer(modifier = Modifier.width(10.dp))
                    IconItem(painter = painterResource(id = R.drawable.qr_scanner_foreground)) {
                        navController.navigate("QRAddScreen")
                    } //Right ; QR input
                }
                AnimatedVisibility(
                    visible = isVisible,
                    enter = slideIn(tween(500, easing = LinearOutSlowInEasing)){fullSize ->
                        IntOffset(-(fullSize.width / 2) , -(fullSize.height / 2) )
                    },
                    exit = slideOut(tween(500, easing = LinearOutSlowInEasing)) {fullSize ->
                        IntOffset(-(fullSize.width / 2) , -(fullSize.height / 2) )
                    } + fadeOut(),
                ) {
                    Spacer(modifier = Modifier.width(10.dp))
                    IconItem(painter = painterResource(id = R.drawable.progress_monitor)) {
                        if(soloSwitch.value){
                            navController.navigate("InputsScreen/${404L}")
                        }else{
                            navController.navigate("InputsScreen/${trainingBuddyId.value.id}")
                        }
                    }// diagonal lower left; MaxPR Input
                }
            }
        }
    }
}