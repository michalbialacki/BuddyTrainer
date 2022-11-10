package com.kkp.buddytrainer.presentation.trainingScreen.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kkp.buddytrainer.domain.model.Person
import com.kkp.buddytrainer.presentation.trainingScreen.TrainingScreenViewModel


@Composable
fun TrainingScreenTopBar(
    viewModel: TrainingScreenViewModel = hiltViewModel(),
    navController: NavController,
    buddyUser : Person,
    mainUser : Person
) {
    var soloSwitch by remember {
        mutableStateOf(viewModel.buddySwitch)
    }
    var trainingBuddy by remember{
        mutableStateOf(mainUser)
    }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.DarkGray),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        if(!soloSwitch.value){
            trainingBuddy = mainUser
            viewModel.currentUser.value = mainUser
        }else{
            trainingBuddy = buddyUser
            viewModel.currentUser.value = buddyUser
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            contentAlignment = Alignment.CenterEnd
        ){
            Switch(checked = soloSwitch.value, onCheckedChange = {
                viewModel.updateSwitchState(trainingBuddy)
            } )
        }

    }

}