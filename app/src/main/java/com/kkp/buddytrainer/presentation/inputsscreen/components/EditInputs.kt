package com.kkp.buddytrainer.presentation.inputsscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkp.buddytrainer.domain.model.Person
import com.kkp.buddytrainer.presentation.inputsscreen.InputScreenViewModel
import com.kkp.buddytrainer.ui.theme.BuddyTrainerTheme
import com.kkp.buddytrainer.ui.theme.Teal200

@Composable
fun EditInputs(
    person : Person,
//    viewModel: InputScreenViewModel = hiltViewModel()
) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Teal200),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
            ) {
            SDBTextfield(exerciseRecordValue = person.Bench)
        }
    }


}
