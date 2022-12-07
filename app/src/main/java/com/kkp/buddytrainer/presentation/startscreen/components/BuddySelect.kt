package com.kkp.buddytrainer.presentation.startscreen.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kkp.buddytrainer.presentation.startscreen.StartScreenViewModel

@Composable
fun BuddySelect(
    viewModel: StartScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val expanded = remember{ mutableStateOf(false) }
    val buddiesList by remember {viewModel.buddiesList}
    var selectedIndex by remember { mutableStateOf(0) }
    val isLoading by remember { viewModel.isLoading}
    val errorOccurred by remember {viewModel.errorOccurred}
    var mainUser by remember {viewModel.mainUser}
    var selectedBuddy by remember { mutableStateOf(viewModel.selectedBuddy.value) }
    var soloTrainingSwitch by remember {viewModel.soloTrainingSwitch}

    LaunchedEffect(true){
        viewModel.fetchMainUser()
        mainUser = viewModel.mainUser.value
        viewModel.getUsers()

    }


    when(isLoading){
        true -> {
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }
        false && !errorOccurred -> {
            AnimatedVisibility(visible = !soloTrainingSwitch) {
                Button(onClick = {
                    expanded.value = !expanded.value
                }) {
                    if (buddiesList.size > 0 && !soloTrainingSwitch){
                        Text(text = selectedBuddy.Name)
                        DropdownMenu(
                            expanded = expanded.value,
                            onDismissRequest = { expanded.value = false },
                            modifier = Modifier
                                .fillMaxWidth(0.55f)
                                .background(
                                    MaterialTheme.colors.background
                                )
                        ){
                            Column(
                                modifier = Modifier
                                    .height(120.dp)
                                    .verticalScroll(rememberScrollState()),
                            ){
                                buddiesList.forEachIndexed { index, item ->
                                    DropdownMenuItem(
                                        onClick = {
                                            selectedIndex = index
                                            expanded.value = false
                                            selectedBuddy = item
                                            viewModel.buddySelected(item)
                                        },
                                    ) {
                                        Box(modifier = Modifier
                                            .fillMaxWidth(),contentAlignment = Alignment.Center){
                                            Text(text = item.Name, textAlign = TextAlign.Center,
                                                /*modifier = Modifier.pointerInput(Unit){
                                                detectTapGestures (
                                                    onLongPress = {
                                                        Toast.makeText(context,"Here",Toast.LENGTH_SHORT).show()
                                                        viewModel.deleteBuddy(item) }
                                                )
                                            }*/
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.size(60.dp))
            }
            Spacer(modifier = Modifier.size(60.dp))
            Button(onClick = {
                var buddyId = 213742069L
                if(!soloTrainingSwitch && selectedBuddy.id != 404L){
                    buddyId = selectedBuddy.id
                }
                navController.navigate("TrainingScreen/${mainUser.trainingDay}/${buddyId}")
            }) {
                Text(text = "Start the workout of the day!")
            }
        }
        else -> Text(text = "Error occurred!")
    }
}