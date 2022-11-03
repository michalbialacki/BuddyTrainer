package com.kkp.buddytrainer.presentation.trainingScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkp.buddytrainer.domain.model.Exercise
import com.kkp.buddytrainer.presentation.trainingScreen.TrainingScreenViewModel

@Composable
fun ExerciseColumn(
    viewModel: TrainingScreenViewModel = hiltViewModel(),
    response : MutableList<Exercise>
) {
    var currentUser = remember{ viewModel.getMainUser() }

    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)) {
                if (response.isNotEmpty()){
                    items(response){ exercise ->
                       ExerciseCard(exercise = exercise, currentUser)
                    }
                }else{
                    items(1){
                        Text(text = "There is something wrong. Contact me and describe how it happened")
                    }
                }
        }
    }

}