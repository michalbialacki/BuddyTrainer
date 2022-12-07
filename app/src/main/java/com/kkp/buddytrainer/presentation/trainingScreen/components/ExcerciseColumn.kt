package com.kkp.buddytrainer.presentation.trainingScreen.components

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kkp.buddytrainer.domain.model.Exercise
import com.kkp.buddytrainer.domain.model.Person
import com.kkp.buddytrainer.presentation.trainingScreen.TrainingScreenViewModel

@ExperimentalMaterialApi
@Composable
fun ExerciseColumn(
    viewModel: TrainingScreenViewModel = hiltViewModel(),
    response : MutableList<Exercise>,
    mainUser : Person,
    navController: NavController
) {

    var currentUser by remember { mutableStateOf(mainUser) }
    val currentList = remember { mutableStateListOf<Exercise>() }
    val initialListSize = remember { viewModel.initialSize }
    var progress = remember { mutableStateOf(0) }
    var isVisible by remember { mutableStateOf(false)}
    var switchState: Boolean by remember { mutableStateOf(viewModel.buddySwitch.value) }
    currentList.swapList(response)


    BackHandler {
        if (progress.value == initialListSize.value) {
            viewModel.updateMainUserTrainingDay()
        }
        navController.navigate("StartScreen") {
            popUpTo("StartScreen") { inclusive = true }
        }
    }
    LaunchedEffect(true){
        isVisible = true
    }


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            switchState = viewModel.buddySwitch.value
            currentUser = mainUser
            if (progress.value != initialListSize.value) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    if (currentList.isNotEmpty() || initialListSize.value > 0) {
                        itemsIndexed(items = currentList, key = {index, item -> item.hashCode()}) { index, exercise ->
                            val dismissState = rememberDismissState()

                            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                                currentList.removeAt(index)
                                progress.value += 1
                            }
                            SwipeToDismiss(
                                state = dismissState,
                                modifier = Modifier
                                    .padding(vertical = Dp(1f)),
                                directions = setOf(
                                    DismissDirection.EndToStart
                                ),
                                dismissThresholds = { direction ->
                                    FractionalThreshold(if (direction == DismissDirection.EndToStart) 0.1f else 0.05f)
                                },
                                background = {
                                    val color by animateColorAsState(
                                        when (dismissState.targetValue) {
                                            DismissValue.Default -> Color.White
                                            else -> Color.Red
                                        }
                                    )
                                    val alignment = Alignment.CenterEnd
                                    val icon = Icons.Default.Delete

                                    val scale by animateFloatAsState(
                                        if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
                                    )

                                    Box(
                                        Modifier
                                            .fillMaxSize()
                                            .background(color)
                                            .padding(horizontal = Dp(20f)),
                                        contentAlignment = alignment
                                    ) {
                                        Icon(
                                            icon,
                                            contentDescription = "Delete Icon",
                                            modifier = Modifier.scale(scale)
                                        )
                                    }
                                },
                                dismissContent = {
                                    Card(
                                        elevation = animateDpAsState(
                                            if (dismissState.dismissDirection != null) 4.dp else 0.dp
                                        ).value,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(Dp(50f))
                                            .align(alignment = Alignment.CenterVertically)
                                    ) {
                                        if(!switchState){
                                            ExerciseCard(exercise = exercise, currentUserInput = currentUser) {

                                            }
                                        }else{
                                            ExerciseCard(exercise = exercise, currentUserInput = currentUser) {

                                            }
                                        }
                                    }
                                }
                            )
                        }
                    } else {
                        items(1) {
                            Text(text = "There is something wrong. Contact me and describe how it happened")
                        }
                    }
                }
            } else {
                if (progress.value != 0) {
                    Text(text = "Good Job!")
                }
            }
        }
    }
}

fun <T> SnapshotStateList<T>.swapList(newList: List<T>){
    clear()
    addAll(newList)
}

