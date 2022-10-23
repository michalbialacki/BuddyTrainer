package com.kkp.buddytrainer.presentation.addbuddyscreen

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.kkp.buddytrainer.presentation.addbuddyscreen.components.BuddyCreation

@Composable
fun AddBuddyScreen(
    navController: NavController,
) {
    Surface {
        BuddyCreation()

    }
}