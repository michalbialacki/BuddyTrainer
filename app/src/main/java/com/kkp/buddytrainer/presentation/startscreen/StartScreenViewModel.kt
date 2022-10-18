package com.kkp.buddytrainer.presentation.startscreen

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
class StartScreenViewModel @Inject constructor(
    private val personRepo : PersonRepository
)
    : ViewModel() {

        var person : MutableState<Person> = mutableStateOf(Person(0f,0f,0f,"",0))
        init {
            getUser()
        }


    fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
//            TODO("Create a main user Personal Record and grab his maxes from Db")
            try {
                personRepo.getPersonFromRoom(213742069).collect {
                    person.value = it
                }
            } catch (e:Exception){
                Log.d("GetMainUser", "User not found")
            }
        }
    }

}