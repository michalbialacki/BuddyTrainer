package com.kkp.buddytrainer.presentation.trainingScreen.components

import android.util.Log
import android.view.Surface
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.kkp.buddytrainer.core.Resource
import com.kkp.buddytrainer.domain.model.Exercise
import com.kkp.buddytrainer.presentation.trainingScreen.TrainingScreenViewModel




@Composable
fun ExerciseColumn(
    viewModel: TrainingScreenViewModel = hiltViewModel(),
    response : MutableList<Exercise>,
    navController: NavController
) {

    var currentUser = remember{ viewModel.getMainUser() }
    var currentList = remember{ viewModel.cachedExerciseList }
    val initialListSize = remember {viewModel.initialSize}
    var progress = remember { mutableStateOf(0)}
    val lifecycleOwner = LocalLifecycleOwner.current

    BackHandler {
        if (progress.value == initialListSize.value){
            viewModel.updateMainUserTrainingDay()
        }
        navController.navigate("StartScreen"){
            popUpTo("StartScreen"){inclusive = true}
        }
    }




    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            if(progress.value != initialListSize.value){
                LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)) {
                    if (currentList.isNotEmpty() || initialListSize.value > 0){
                        items(currentList){ exercise ->
                            ExerciseCard(exercise = exercise, currentUser){
                                viewModel.updateExerciseList(exercise)
                                progress.value +=1
                            }

                        }
                    }else{
                        items(1){
                            Text(text = "There is something wrong. Contact me and describe how it happened")
                        }
                    }
                }
            }else{
                if (progress.value != 0){
                    Text(text = "Good Job!")
                }
            }
        }
    }

}