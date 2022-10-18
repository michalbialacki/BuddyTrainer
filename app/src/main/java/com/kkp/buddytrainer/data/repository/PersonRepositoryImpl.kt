package com.kkp.buddytrainer.data.repository

import com.kkp.buddytrainer.data.network.PersonDAO
import com.kkp.buddytrainer.domain.model.Person
import com.kkp.buddytrainer.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow

class PersonRepositoryImpl(
    private val personDAO: PersonDAO
) : PersonRepository {
    override suspend fun getBuddiesFromRoom(): Flow<List<Person>> = personDAO.getBuddies()

    override suspend fun getPersonFromRoom(id : Int): Flow<Person> = personDAO.getPerson(id)

    override suspend fun addBuddyToRoom(person: Person) = personDAO.addBuddy(person)

    override suspend fun deleteBuddyFromRoom(person: Person) = personDAO.deleteBuddy(person)
}