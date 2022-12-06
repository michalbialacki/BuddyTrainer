package com.kkp.buddytrainer.presentation.inputsscreen.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkp.buddytrainer.domain.model.Bench
import com.kkp.buddytrainer.domain.model.Deadlift
import com.kkp.buddytrainer.domain.model.Person
import com.kkp.buddytrainer.domain.model.Squat
import com.kkp.buddytrainer.presentation.inputsscreen.InputScreenViewModel
import com.kkp.buddytrainer.ui.theme.Teal200

@Composable
fun EditInputs(
    person : Person,
    viewModel: InputScreenViewModel = hiltViewModel()
) {

    var localFocus = LocalFocusManager.current
    var interactionSource = remember{ MutableInteractionSource()}
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if(person.id == 213742069L){
            Text(text = "Your PRs", modifier = Modifier.height(20.dp), textAlign = TextAlign.Center)
        }else{
            Text(text = "${person.Name.split(" ")[0]}'s PRs", modifier = Modifier.height(20.dp), textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier.size(20.dp))
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color.LightGray)
                .clickable(indication = null, interactionSource = interactionSource) {
                    localFocus.clearFocus()
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {


            SDBTextfield(exerciseRecordValue = person.Bench, exerciseName = "Bench press"){
                viewModel.updateBench(Bench(id = person.id,Bench = it))
            }
            SDBTextfield(exerciseRecordValue = person.Squat, exerciseName = "Squat"){
                viewModel.updateSquat(Squat(id = person.id, Squat = it))
            }
            SDBTextfield(exerciseRecordValue = person.Deadlift, exerciseName = "Deadlift"){
                viewModel.updateDeadlift(Deadlift(id = person.id, Deadlift = it))
            }
        }
    }


}
