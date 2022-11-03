package com.kkp.buddytrainer.presentation.trainingScreen

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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrainingScreenViewModel @Inject constructor(
    private val personRepo : PersonRepository
) : ViewModel() {

    private var mainUser : MutableState<Person> = mutableStateOf(Person(trainingDay = 1))
    private val database = Firebase.database("https://buddytrainer-b54d4-default-rtdb.europe-west1.firebasedatabase.app/")
    var response : MutableState<Resource> = mutableStateOf(Resource.Empty)
    var index : MutableState<Int> = mutableStateOf(0)


    init {
        fetchMainUser()
    }

    fun updateIndex() = viewModelScope.launch(Dispatchers.IO) {
        when(index.value){
            in (0..2) -> {
                index.value ++
            }
            else -> {
                index.value = 0
            }
        }
        Log.d("Check", "${index.value}")
    }

    fun getMainUser() : Person{
        return mainUser.value
    }

    fun fetchMainUser() = viewModelScope.launch(Dispatchers.IO) {
        personRepo.getPersonFromRoom(213742069L).collect {
            mainUser.value = it
        }
    }

    fun fetchFirebaseTraining(dayPathIndex : Int) = viewModelScope.launch(Dispatchers.IO) {
        response.value = Resource.Loading
        var trainingRef = database.getReference("Day"+"${dayPathIndex}")
        val temp = mutableListOf<Exercise>()
        trainingRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children){
                    if(item.exists()){
                        temp.add(item.getValue(Exercise::class.java)!!)
                    }
                    response.value = Resource.Success(temp)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                response.value = Resource.Empty
            }
        })
    }

}