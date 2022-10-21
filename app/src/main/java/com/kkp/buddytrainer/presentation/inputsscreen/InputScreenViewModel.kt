package com.kkp.buddytrainer.presentation.inputsscreen

import android.content.PeriodicSync
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkp.buddytrainer.domain.model.Person
import com.kkp.buddytrainer.domain.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class InputScreenViewModel @Inject constructor(
    private val personRepo : PersonRepository
): ViewModel() {

    val mainUser : MutableState<Person> = mutableStateOf<Person>(Person())
    val buddyUser : MutableState<Person> = mutableStateOf<Person>(Person())
    var isLoading = mutableStateOf(false)
    var errorOccurred = mutableStateOf(false)




    fun getUsers(personId : Long) = viewModelScope.launch(Dispatchers.IO) {
        isLoading.value = true
        try {
            if (personId != 404L){
                personRepo.getPersonFromRoom(personId).collect {
                    buddyUser.value = it
                    isLoading.value = false
                }
            }else{
                /*If dummy is passed - get mainUser*/
                personRepo.getPersonFromRoom(213742069L).collect {
                    mainUser.value = it
                    isLoading.value = false
                    Log.d("MainUserCheck", "${mainUser.value}")
                }
            }
        }catch (e:Exception){
            Log.d("Error occurred", "$e")
            errorOccurred.value = true
            isLoading.value = false
        }

    }

}