package com.kkp.buddytrainer.presentation.startscreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kkp.buddytrainer.core.Resource
import com.kkp.buddytrainer.domain.model.Exercise
import com.kkp.buddytrainer.domain.model.Person
import com.kkp.buddytrainer.domain.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class StartScreenViewModel @Inject constructor(
    private val personRepo : PersonRepository
)
    : ViewModel() {


        private val dummy = Person(
            id = 404L,
            Bench = 0f,
            Squat = 0f,
            Deadlift = 0f,
            Name = ""
        )
        var mainUser : MutableState<Person> = mutableStateOf(dummy)
        var isLoading = mutableStateOf(false)
        var buddiesList = mutableStateOf<List<Person>>(emptyList<Person>())
        var errorOccurred = mutableStateOf(false)
        var soloTrainingSwitch = mutableStateOf(false)
        var selectedBuddy : MutableState<Person> = mutableStateOf(dummy.copy(
            Name = "Choose your buddy!"
        ))
        private var buddyListSize : MutableState<Int> = mutableStateOf(0)





    fun fetchMainUser() = viewModelScope.launch(Dispatchers.IO){
        personRepo.getPersonFromRoom(213742069L).collect {
            mainUser.value = it
        }
    }

    fun getUsers() = viewModelScope.launch(){
        isLoading.value = true
        try {
            personRepo.getBuddiesFromRoom().collect {
                val tempMap = it.mapIndexed { index, person ->
                    person
                }
                buddiesList.value = tempMap
                if(buddiesList.value.isNotEmpty()){
                    mainUser.value = buddiesList.value.find { it.id == 213742069L } ?: dummy
                    val mainUserIndex = buddiesList.value.indexOf(mainUser.value)
                    buddiesList.value = buddiesList.value.minus(mainUser.value)
                }
                buddyListSize.value = buddiesList.value.size
                isLoading.value = false
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main){
                errorOccurred.value = true
                Log.d("GetUsers", "Users not found; $e")
            }
        }
    }
    fun switchBuddySwitch(){
        viewModelScope.launch(Dispatchers.IO) {
            soloTrainingSwitch.value = !soloTrainingSwitch.value
        }
    }

    fun buddySelected(person: Person){
        viewModelScope.launch(Dispatchers.IO) {
            selectedBuddy.value = person
        }
    }
    fun deleteBuddy(person: Person) = viewModelScope.launch(Dispatchers.IO) {
        personRepo.deleteBuddyFromRoom(person = person)
    }


}