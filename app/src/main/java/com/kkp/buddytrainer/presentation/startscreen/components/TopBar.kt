package com.kkp.buddytrainer.presentation.startscreen.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kkp.buddytrainer.presentation.startscreen.StartScreenViewModel

@Composable
fun TopBar(
    viewModel: StartScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    var soloSwitch by remember {
        mutableStateOf(viewModel.soloTrainingSwitch)
    }
    val trainingBuddyId by remember{
        mutableStateOf(viewModel.selectedBuddy)
    }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
//            .offset(y=-50.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart){
            IconMenu(navController = navController)
            /*Button(onClick = {
                if(soloSwitch.value){
                    navController.navigate("InputsScreen/${404L}")
                }else{
                    navController.navigate("InputsScreen/${trainingBuddyId.value.id}")
                }
            }) {
                Text(text = "Inputs")
            } */
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            contentAlignment = Alignment.CenterEnd
        ){
            Switch(
                checked = soloSwitch.value,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colors.primary,
                    uncheckedThumbColor = MaterialTheme.colors.secondary,
                    checkedTrackColor = MaterialTheme.colors.primaryVariant,
                    uncheckedTrackColor = MaterialTheme.colors.secondaryVariant,
                ),
                onCheckedChange = {
                viewModel.switchBuddySwitch()
            } )
        }

    }

}