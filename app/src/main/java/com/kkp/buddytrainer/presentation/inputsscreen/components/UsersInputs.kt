package com.kkp.buddytrainer.presentation.inputsscreen.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkp.buddytrainer.presentation.inputsscreen.InputScreenViewModel

@Composable
fun UsersInputs(
    userId : Long,
    viewModel : InputScreenViewModel = hiltViewModel()
) {

    val mainUser by remember{viewModel.mainUser}
    val buddyUser by remember {viewModel.buddyUser}
    val isLoading by remember{viewModel.isLoading}
    var isVisible by remember{ mutableStateOf(false)}

    LaunchedEffect(true){
        viewModel.getUsers(userId)
        viewModel.getUsers(404L)
        isVisible = true
    }

    Box(
        modifier = Modifier.clip(RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center) {

        Row(modifier = Modifier
            .background(MaterialTheme.colors.background)
            )
        {
            AnimatedVisibility(
                visible = isVisible,
                enter = slideInVertically() + fadeIn(),
                exit = slideOutVertically() + fadeOut()
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(480.dp)
                    .clip(RoundedCornerShape(10.dp)),
                    verticalAlignment  = Alignment.CenterVertically)
                {
                    if (!isLoading) {
                        Column(
                            modifier = Modifier
                                .weight(1f),
                            verticalArrangement = Arrangement.Center
                        ) {
                            EditInputs(person = mainUser)
                        }
                        if (userId != 404L) {
                            Column(
                                modifier = Modifier
                                    .weight(1f),
                                verticalArrangement = Arrangement.Center
                            ) {
                                EditInputs(person = buddyUser)
                            }
                        }
                    } else {
                        CircularProgressIndicator(color = MaterialTheme.colors.primaryVariant)
                    }
                }
            }
        }
    }
}