package com.kkp.buddytrainer.presentation.inputsscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkp.buddytrainer.presentation.inputsscreen.InputScreenViewModel
import com.kkp.buddytrainer.presentation.startscreen.components.BuddySelect
import com.kkp.buddytrainer.ui.theme.Teal200

@Composable
fun UsersInputs(
    userId : Long,
    viewModel : InputScreenViewModel = hiltViewModel()
) {

    val mainUser by remember{viewModel.mainUser}
    val buddyUser by remember {viewModel.buddyUser}
    val isLoading by remember{viewModel.isLoading}

    LaunchedEffect(true){
        viewModel.getUsers(userId)
        viewModel.getUsers(404L)
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxSize()){
            if(!isLoading){
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.Magenta),
                    verticalArrangement = Arrangement.Center
                ){
                    EditInputs(person = mainUser)
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(0.5f)
                        .background(Teal200),
                    verticalArrangement = Arrangement.Center
                ){
                    Spacer(modifier = Modifier.size(25.dp))
                    ExerciseNamesColumn()
                }
                if (userId != 404L){
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .background(Color.Red),
                        verticalArrangement = Arrangement.Center
                    ){
                        EditInputs(person = buddyUser)
                    }
                }
            }else{
                CircularProgressIndicator(color = MaterialTheme.colors.primaryVariant)
            }
        }
    }
}