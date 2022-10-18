package com.kkp.buddytrainer.domain.repository

import com.kkp.buddytrainer.domain.model.Person
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    suspend fun getBuddiesFromRoom() : Flow<List<Person>>
    suspend fun getPersonFromRoom(id : Int) : Flow<Person>
    suspend fun addBuddyToRoom(person: Person)
    suspend fun deleteBuddyFromRoom(person: Person)
}