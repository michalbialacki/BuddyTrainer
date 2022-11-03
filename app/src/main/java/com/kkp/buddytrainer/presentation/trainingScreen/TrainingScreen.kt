package com.kkp.buddytrainer.presentation.trainingScreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kkp.buddytrainer.core.Resource
import com.kkp.buddytrainer.presentation.trainingScreen.components.ExerciseColumn

@Composable
fun TrainingScreen(
    navController: NavController,
    userTrainingDay : Int,
    viewModel : TrainingScreenViewModel = hiltViewModel()
) {
    var response by remember { viewModel.response }

    LaunchedEffect(true){
        viewModel.fetchFirebaseTraining(userTrainingDay)
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            when(response){
                is Resource.Success ->{
                    val exerciseList = (response as Resource.Success).data
                    ExerciseColumn(response = exerciseList)
                }
                is Resource.Loading -> CircularProgressIndicator(color = MaterialTheme.colors.primaryVariant)
                is Resource.Empty -> {
                    Text(text = "This empty")
                }
            }
        }
    }

}