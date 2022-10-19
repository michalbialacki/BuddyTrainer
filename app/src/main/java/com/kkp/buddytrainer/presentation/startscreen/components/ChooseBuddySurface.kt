package com.kkp.buddytrainer.presentation.startscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkp.buddytrainer.presentation.startscreen.StartScreenViewModel

@Composable
fun ChooseBuddySurface(
    viewModel: StartScreenViewModel = hiltViewModel()
) {
    val expanded = remember{ mutableStateOf(false) }
    val testList1 = listOf<String>("A","B","C","D")
    val testList2 = remember {viewModel.buddiesList}
    var selectedIndex by remember { mutableStateOf(0) }
    var checkedText by remember { mutableStateOf("Choose your buddy") }
    val isLoading by remember { viewModel.isLoading}
    val errorOccurred by remember {viewModel.errorOccurred}

    Column(
        modifier = Modifier
            .fillMaxSize(0.8f)
            .background(Color.Magenta),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                if (isLoading){
                    CircularProgressIndicator(color = MaterialTheme.colors.primary)
                }else if(!errorOccurred){
                    Button(onClick = { expanded.value = !expanded.value }) {
                        if (testList2.size > 0){
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
                                testList2.forEachIndexed { index, item ->
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
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Start the workout of the day!")
                    }
                } else{
                    Text(text = "Error occurred. Reopen the app")
                }
            }
        Column(
            modifier = Modifier.weight(0.5f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row {
                    Text(text = "You can add your buddy ")
                    Text(text = "manually")
                }
            Row {
                Text(text = "or")
            }
                Row {
                    Text(text = "you can use his ")
                    Text(text = "QR Code")
                }

        }
    }
}