package com.kkp.buddytrainer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kkp.buddytrainer.inputsscreen.presentation.InputsScreen
import com.kkp.buddytrainer.presentation.addbuddyscreen.AddBuddyScreen
import com.kkp.buddytrainer.presentation.qrscreen.QRAddBuddyScreen
import com.kkp.buddytrainer.presentation.startscreen.StartScreen
import com.kkp.buddytrainer.presentation.trainingScreen.TrainingScreen
import com.kkp.buddytrainer.ui.theme.BuddyTrainerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuddyTrainerTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "StartScreen"){
                    composable(route = "StartScreen"){
                        StartScreen(navController = navController)
                    }
                    composable(route = "TrainingScreen/{userTrainingDay}/{buddyId}", arguments = listOf(
                        navArgument("userTrainingDay"){
                            type = NavType.IntType
                        },
                        navArgument("buddyId"){
                            type = NavType.LongType
                        }
                    )
                    ){
                        val userTrainingDay = remember{
                            it.arguments?.getInt("userTrainingDay")
                        }
                        val buddyId = remember{
                            it.arguments?.getLong("buddyId")
                        }
                        TrainingScreen(
                            navController = navController,
                            userTrainingDay = userTrainingDay ?: 0,
                            buddyId = buddyId ?: 404L
                        )
                    }
                    composable(
                        route = "InputsScreen/{buddyId}",
                        arguments = listOf(
                            navArgument("buddyId"){
                                type = NavType.LongType
                            }
                        )
                    ){
                        val buddyId = remember{
                            it.arguments?.getLong("buddyId")
                        }
                        InputsScreen(
                            navController = navController,
                            buddyId = buddyId ?: 404L)
                    }
                    composable("AddBuddyScreen"){
                        AddBuddyScreen(navController = navController)
                    }
                    composable("QRAddScreen"){
                        QRAddBuddyScreen(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BuddyTrainerTheme {
        Greeting("Android")
    }
}