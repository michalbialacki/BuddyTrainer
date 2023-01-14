package com.kkp.buddytrainer.presentation.startscreen.components

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kkp.buddytrainer.presentation.startscreen.StartScreenViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun IconPopdMenu(
    navController: NavController,
    viewModel : StartScreenViewModel = hiltViewModel()
) {
    var mainUser by remember {viewModel.mainUser}

    var soloSwitch by remember {
        mutableStateOf(viewModel.soloTrainingSwitch)
    }
    val trainingBuddy by remember{
        mutableStateOf(viewModel.selectedBuddy)
    }
    var isVisible by remember {
        mutableStateOf(false)
    }
    val passedOffset = listOf<State<Dp>>(
        animateDpAsState(if (isVisible) 30.dp else 0.dp),
        animateDpAsState(if (isVisible) 15.dp else 0.dp),
        animateDpAsState(if (isVisible) (-30).dp else 0.dp),
        animateDpAsState(if (isVisible) (-30).dp else 0.dp),
        animateDpAsState(if (isVisible) 15.dp else 0.dp)
    )


    Box {
        Row {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally) {
                /*IconItem(painter = painterResource(id = R.drawable.menu_hamburger)) {
                    isVisible = !isVisible


                } // Show/Hide menu action button */

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                    AnimatedVisibility(
                        visible = isVisible,
                        enter = scaleIn(tween(500)) + fadeIn(),
                        exit = scaleOut(tween(500)) + fadeOut()
                    ) {
                        Spacer(modifier = Modifier.width(10.dp))
                        IconGif(com.kkp.buddytrainer.R.drawable.qrscan, cstmModifeir = Modifier.offset(y=passedOffset[1].value)) {
                            navController.navigate("QRAddScreen")
                        } //Right ; QR input
                    }
                    AnimatedVisibility(
                        visible = isVisible,
                        enter = scaleIn(tween(500)) + fadeIn(),
                        exit = scaleOut(tween(500)) + fadeOut(),
                    ) {
                        Spacer(modifier = Modifier.width(10.dp))
                        IconGif(com.kkp.buddytrainer.R.drawable.compare, cstmModifeir = Modifier.offset(y=passedOffset[2].value)) {
                            if(soloSwitch.value){
                                navController.navigate("InputsScreen/${404L}")
                            }else{
                                navController.navigate("InputsScreen/${trainingBuddy.value.id}")
                            }
                        }// diagonal lower left; MaxPR Input

                    }

                    AnimatedVisibility(
                        visible = isVisible,
                        enter = scaleIn(tween(500)) + fadeIn(),
                        exit = scaleOut(tween(500)) + fadeOut()
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        IconGif(gifId = com.kkp.buddytrainer.R.drawable.muscle, cstmModifeir = Modifier.offset(y=passedOffset[3].value)){
                            var buddyId = 213742069L
                            if(!(soloSwitch.value) && trainingBuddy.value.id != 404L){
                                buddyId = trainingBuddy.value.id
                            }
                            navController.navigate("TrainingScreen/${mainUser.trainingDay}/${trainingBuddy.value.id}")                        } // Start training
                    }

                    AnimatedVisibility(
                        visible = isVisible,
                        enter = scaleIn(tween(500)) + fadeIn(),
                        exit = scaleOut(tween(500)) + fadeOut()
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        IconGif(gifId = com.kkp.buddytrainer.R.drawable.addbuddy, cstmModifeir = Modifier.offset(y=passedOffset[4].value)){
                            navController.navigate("AddBuddyScreen")
                        } // DOWN; Manual input
                    }


                }

                IconGif(gifId = com.kkp.buddytrainer.R.drawable.gymicon, cstmModifeir = Modifier.offset(y=passedOffset[0].value)){
                    isVisible = !isVisible
                    Log.d("IconPopd", "Process is on")
                }
            }
        }
    }
}