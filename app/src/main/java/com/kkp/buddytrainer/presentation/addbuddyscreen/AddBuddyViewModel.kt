package com.kkp.buddytrainer.presentation.addbuddyscreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkp.buddytrainer.domain.model.Person
import com.kkp.buddytrainer.domain.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class AddBuddyViewModel @Inject constructor(
    private val personRepository: PersonRepository
) : ViewModel(){

    private var newBudPRs : MutableList<Float> = mutableListOf(0f,0f,0f)
    private var buddyName : MutableState<String> = mutableStateOf("")


    fun checkIfBuddyReady() : Boolean{
        if (buddyName.value.isNotEmpty() && !newBudPRs.contains(0f))return true else return false
    }

    fun addBuddyToRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            if (newBudPRs[0] * newBudPRs[1] * newBudPRs[2] != 0f || buddyName.value.length > 0) {
                val buddy = Person(
                    id = Calendar.getInstance().timeInMillis,
                    Name = buddyName.value,
                    Bench = newBudPRs[0],
                    Squat = newBudPRs[1],
                    Deadlift = newBudPRs[2]
                )
                personRepository.addBuddyToRoom(person = buddy)
            }
        }
    }

    fun changeBuddyPR(index : Int = 0, newPR : Float) = viewModelScope.launch(Dispatchers.IO) {
        newBudPRs[index] = newPR
    }

    fun changeBuddyName(name:String) = viewModelScope.launch(Dispatchers.IO) {
        buddyName.value = name
        Log.d("Name", "${buddyName.value}")
    }

}