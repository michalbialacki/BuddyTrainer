package com.kkp.buddytrainer.presentation.trainingScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
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
import com.kkp.buddytrainer.domain.model.TrainingDay
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

    private var mainUser : MutableState<Person> = mutableStateOf(Person(trainingDay = 0))
    private val database = Firebase.database("https://buddytrainer-b54d4-default-rtdb.europe-west1.firebasedatabase.app/")
    var buddySwitch : MutableState<Boolean> = mutableStateOf(false)
    var buddyUser : MutableState<Person> = mutableStateOf(Person())
    var response : MutableState<Resource> = mutableStateOf(Resource.Empty)
    var cachedExerciseList = mutableStateListOf<Exercise>()
    var initialSize : MutableState<Int> = mutableStateOf(0)
    var currentUser : MutableState<Person> = mutableStateOf(mainUser.value)
    private var exercisePR : MutableState<Float> = mutableStateOf(0f)


    init {
        fetchMainUser()
    }


    fun getMainUser() : Person{
        return mainUser.value
    }
    fun getBuddyUser() : Person{
        return buddyUser.value
    }


    fun fetchBuddy(buddyId : Long) = viewModelScope.launch(Dispatchers.IO){
        if(buddyId != 404L){
            personRepo.getPersonFromRoom(buddyId).collect {
                buddyUser.value = it
            }
        }
    }

    fun updateMainUserTrainingDay() = viewModelScope.launch(Dispatchers.IO){
        if (mainUser.value.trainingDay == 20){
            personRepo.updateTrainingDay(
                TrainingDay(id = mainUser.value.id, trainingDay = 0)
            )
        } else{
            val trainingDay = TrainingDay(id = mainUser.value.id , trainingDay = mainUser.value.trainingDay + 1  )
            personRepo.updateTrainingDay(trainingDay)
        }

    }

    fun updateSwitchState(person : Person) = viewModelScope.launch(Dispatchers.IO){
        buddySwitch.value = !buddySwitch.value
        currentUser.value = person
    }

    fun fetchMainUser() = viewModelScope.launch(Dispatchers.IO) {
        personRepo.getPersonFromRoom(213742069L).collect {
            mainUser.value = it
        }
    }

    fun updateExerciseList (exercise : Exercise) = viewModelScope.launch(Dispatchers.IO) {
        cachedExerciseList.remove(exercise)
        cachedExerciseList.replaceAll { it.copy() }
    }

    fun cacheList(items : MutableList<Exercise>) = viewModelScope.launch(Dispatchers.IO) {
        cachedExerciseList.clear()
        cachedExerciseList.addAll(items)
        initialSize.value = items.size
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