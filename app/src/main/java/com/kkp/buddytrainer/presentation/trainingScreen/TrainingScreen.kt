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
import com.kkp.buddytrainer.domain.model.Person
import com.kkp.buddytrainer.presentation.trainingScreen.components.ExerciseColumn
import com.kkp.buddytrainer.presentation.trainingScreen.components.TrainingScreenTopBar

@Composable
fun TrainingScreen(
    navController: NavController,
    userTrainingDay : Int,
    buddyId : Long,
    viewModel : TrainingScreenViewModel = hiltViewModel()
) {
    var response by remember { viewModel.response }
    var buddyUser by remember { mutableStateOf(viewModel.currentUser.value).also {
        viewModel.fetchBuddy(buddyId)
        viewModel.fetchMainUser()
        }
    }

    LaunchedEffect(true){
        viewModel.fetchFirebaseTraining(userTrainingDay)
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            when(response){
                is Resource.Success ->{
                    buddyUser = viewModel.getBuddyUser()
                    val exerciseList = (response as Resource.Success).data
                    if(!buddyUser.Name.isNullOrEmpty() && buddyUser.id != 213742069L) TrainingScreenTopBar(
                        navController = navController,
                        buddyUser = buddyUser,
                        mainUser = viewModel.getMainUser()
                    )
                    ExerciseColumn(response = exerciseList, navController = navController, mainUser = viewModel.getMainUser())
                    viewModel.cacheList((response as Resource.Success).data)

                }
                is Resource.Loading -> CircularProgressIndicator(color = MaterialTheme.colors.primaryVariant).also {
                    viewModel.fetchBuddy(buddyId)
                    viewModel.fetchMainUser()
                }
                is Resource.Empty -> {
                    Text(text = "This empty")
                }
            }
        }
    }

}