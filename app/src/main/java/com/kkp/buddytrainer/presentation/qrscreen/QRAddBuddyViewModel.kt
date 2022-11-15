package com.kkp.buddytrainer.presentation.qrscreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkp.buddytrainer.domain.model.Person
import com.kkp.buddytrainer.domain.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*
import javax.inject.Inject


@HiltViewModel
class QRAddBuddyViewModel @Inject constructor(
    private val personRepository: PersonRepository
) : ViewModel(){
    private var newUserQR = mutableStateOf<Person>(Person())
    private var errorMonitor = mutableStateOf(false)

    fun addUser(qrResult : String = "") = viewModelScope.launch(Dispatchers.IO) {
        var temp = mutableListOf<String>()
        if(qrResult.isNotEmpty()){
            temp = qrResult.split(" ").toMutableList()
            try {
                newUserQR.value = Person(
                    id = Calendar.getInstance().timeInMillis,
                    Name = "${temp[0]} ${temp[1]}",
                    Bench = temp[2].toFloat(),
                    Squat = temp[3].toFloat(),
                    Deadlift = temp[4].toFloat(),
                    trainingDay = 0
                )
              personRepository.addBuddyToRoom(newUserQR.value)
            } catch (e:Exception){
                errorMonitor.value = true
            }
        }
    }
    fun checkErrorState() : Boolean{
        return errorMonitor.value
    }
}