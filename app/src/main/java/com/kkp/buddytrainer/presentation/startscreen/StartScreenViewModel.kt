package com.kkp.buddytrainer.presentation.startscreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
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
class StartScreenViewModel @Inject constructor(
    private val personRepo : PersonRepository
)
    : ViewModel() {
        private val temp = mutableListOf<String>()
        private val dummy = Person(0f,0f,0f,"",0)
        var mainUser : MutableState<Person> = mutableStateOf(dummy)
        var isLoading = mutableStateOf(true)
        var buddiesList = mutableListOf<Person>()
        var errorOccurred = mutableStateOf(false)

        init {
            getUser()
        }


    fun getUser() {
        viewModelScope.launch {
            if(isLoading.value){
                try {
                    personRepo.getBuddiesFromRoom().collect{
                        buddiesList = it.toMutableList()
                        if(buddiesList.isNotEmpty()){
                            mainUser.value = buddiesList.find { it.id == 213742069L } ?: dummy
                            buddiesList.remove(mainUser.value)
                        }
                        isLoading.value = false
                    }
                } catch (e:Exception){
                    Log.d("GetUsers", "Users not found; $e")
                    errorOccurred.value = true
                }
            }else{
                Log.d("GetMainUser", "Loading finished")

            }
        }
    }

}