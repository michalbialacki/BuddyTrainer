package com.kkp.buddytrainer.presentation.trainingScreen.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkp.buddytrainer.domain.model.Exercise
import com.kkp.buddytrainer.domain.model.Person
import com.kkp.buddytrainer.presentation.trainingScreen.TrainingScreenViewModel

@Composable
fun ExerciseCard(
    exercise: Exercise,
    currentUser : Person,
    viewModel: TrainingScreenViewModel = hiltViewModel(),
    onExeSelected : () -> Unit,
) {
    var exerciseMaxPR = when(exercise.name){
        "Bench Press" -> {currentUser.Bench}
        "Deadlift" -> {currentUser.Deadlift}
        "Squat" -> {currentUser.Squat}
        "Squat MR10 + ExtraVolume" -> {currentUser.Squat}
        "Squat MR10 + BackOff Squat" -> {currentUser.Squat}
        else -> {0f}
    }
    var index by remember { mutableStateOf(0)}
    var reps by remember { mutableStateOf(exercise.reps!![index]) }
    var load by remember { mutableStateOf((exercise.multi!![index] * exerciseMaxPR)) }
    val context = LocalContext.current
    Box(modifier = Modifier.background(Color.LightGray)) {
        Card(modifier = Modifier.pointerInput(Unit){
            detectTapGestures(
                onDoubleTap = {
                    when(index){
                        in (0..((exercise.multi!!.size)-2)) -> {
                            index++
                        }
                        else -> {
                            index = exercise.multi!!.size
                            Toast.makeText(context,"Good job!",Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                onLongPress = {
                    onExeSelected()
                }
            )
        }) {
            Column(modifier = Modifier.padding(4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = exercise.name.toString())
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Text(text = reps)
                    Text(text = if(exerciseMaxPR !=0f){
                        load.toString()
                    }else{
                        "Not specified"
                    })
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    for(i in (0..index-1)){
                        Box(modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.secondary))
                    }
                    for(i in ((exercise.reps!!.size - index - 1) downTo 0)){
                        Box(modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.primary))
                    }
                }
            }
        }
    }

}