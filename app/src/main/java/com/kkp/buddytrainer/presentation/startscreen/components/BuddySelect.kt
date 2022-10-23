package com.kkp.buddytrainer.presentation.startscreen.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkp.buddytrainer.presentation.startscreen.StartScreenViewModel

@Composable
fun BuddySelect(
    viewModel: StartScreenViewModel = hiltViewModel()
) {
    val expanded = remember{ mutableStateOf(false) }
    val buddiesList = remember {viewModel.buddiesList}
    var selectedIndex by remember { mutableStateOf(0) }
    var selectedBudyName by remember { mutableStateOf(viewModel.selectedBuddy.value.Name) }
    val isLoading by remember { viewModel.isLoading}
    val errorOccurred by remember {viewModel.errorOccurred}
    var mainUser by remember {viewModel.mainUser}
    var soloTrainingSwitch by remember {viewModel.soloTrainingSwitch}
    val context = LocalContext.current


    when(isLoading){
        true -> {
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }
        false && !errorOccurred -> {
            Button(onClick = { expanded.value = !expanded.value }) {
                if (buddiesList.value.size > 0 && !soloTrainingSwitch){
                    Text(text = selectedBudyName)
                    DropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false },
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .background(
                                Color.Red
                            )
                    ){
                        buddiesList.value.forEachIndexed { index, item ->
                            DropdownMenuItem(
                                onClick = {
                                selectedIndex = index
                                expanded.value = false
                                selectedBudyName = item.Name
                                viewModel.buddySelected(item)
                                },
                            ) {
                                Text(text = item.Name,
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
                }else{
                    Text(text = "Find a buddy to train with!")
                }
            }
            Spacer(modifier = Modifier.size(120.dp))
            Button(onClick = { /*TODO()*/}) {
                Text(text = "Start the workout of the day!")
            }
        }
        else -> Text(text = "Error occurred!")
    }
}