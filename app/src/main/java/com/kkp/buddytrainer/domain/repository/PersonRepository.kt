package com.kkp.buddytrainer.domain.repository

import com.kkp.buddytrainer.domain.model.*
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    suspend fun getBuddiesFromRoom() : Flow<List<Person>>
    suspend fun getPersonFromRoom(id : Long) : Flow<Person>
    suspend fun addBuddyToRoom(person: Person)
    suspend fun deleteBuddyFromRoom(person: Person)
    suspend fun updateBench(bench: Bench)
    suspend fun updateDeadlift(deadlift: Deadlift)
    suspend fun updateSquat(squat: Squat)
    suspend fun updateTrainingDay(trainingDay: TrainingDay)

}