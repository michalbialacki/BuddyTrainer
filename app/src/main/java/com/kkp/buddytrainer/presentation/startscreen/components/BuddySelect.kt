package com.kkp.buddytrainer.presentation.startscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkp.buddytrainer.presentation.startscreen.StartScreenViewModel

@Composable
fun BuddySelect(
    viewModel: StartScreenViewModel = hiltViewModel()
) {
    val expanded = remember{ mutableStateOf(false) }
    var buddiesList = remember {viewModel.buddiesList}
    var selectedIndex by remember { mutableStateOf(0) }
    var checkedText by remember { mutableStateOf("Choose your buddy") }
    var isLoading by remember { viewModel.isLoading}
    var errorOccurred by remember {viewModel.errorOccurred}
    var mainUser by remember {viewModel.mainUser}



    if (isLoading){
        CircularProgressIndicator(color = MaterialTheme.colors.primary)
    }
    else if(!errorOccurred){
        Button(onClick = { expanded.value = !expanded.value }) {
            if (buddiesList.value.size > 0){
                Text(text = checkedText)
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
                        DropdownMenuItem(onClick = {
                            selectedIndex = index
                            expanded.value = false
                            checkedText = item.Name
                        }) {
                            Text(text = item.Name)
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
    } else{
        Text(text = "Error occurred. Reopen the app")
    }
}