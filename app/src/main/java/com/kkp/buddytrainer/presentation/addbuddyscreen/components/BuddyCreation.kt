package com.kkp.buddytrainer.presentation.addbuddyscreen.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkp.buddytrainer.presentation.addbuddyscreen.AddBuddyViewModel
import com.kkp.buddytrainer.ui.theme.Teal200

@Composable
fun BuddyCreation(
    viewModel : AddBuddyViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier
            .fillMaxSize(0.8f)
            .clip(RoundedCornerShape(10.dp))
            .padding(4.dp)

        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Teal200)
                .padding(4.dp),
                contentAlignment = Alignment.Center
            ){
                Text(text = "Add the budd!")
            }
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Teal200)
                    .clip(RoundedCornerShape(10.dp))
                    .padding(
                        top = 16.dp,
                        bottom = 16.dp,
                        start = 4.dp,
                        end = 4.dp
                    ),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BuddyNameTextInput(onSave = { viewModel.changeBuddyName(it) })
                BuddyParameterTextInput(parName = "Bench Press", onSave = {viewModel.changeBuddyPR(0,it)})
                BuddyParameterTextInput(parName = "Squat", onSave = {viewModel.changeBuddyPR(1,it)})
                BuddyParameterTextInput(parName = "Deadlift", onSave = {viewModel.changeBuddyPR(2,it)})

                Button(
                    onClick = {
                        if(viewModel.checkIfBuddyReady()){
                            viewModel.addBuddyToRoom()
                            Toast.makeText(context,"Buddy added successfully",Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(context,"Confirm all inputs in buddy creation menu",Toast.LENGTH_SHORT).show()
                        }
                     }
                ) {
                    Text(text = "Add the bud!")
                }

            }
        }
    }
}