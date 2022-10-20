package com.kkp.buddytrainer.data.network

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.kkp.buddytrainer.core.Constants.PERSON_TABLE
import com.kkp.buddytrainer.domain.model.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDAO {
    @Query("SELECT * FROM $PERSON_TABLE ORDER BY id ASC")
    fun getBuddies() : Flow<List<Person>>

    @Query("SELECT * FROM $PERSON_TABLE WHERE id = :id")
    fun getPerson(id : Long) : Flow<Person>

    @Insert(onConflict = IGNORE)
    fun addBuddy(buddy: Person)

    @Delete
    fun deleteBuddy(buddy: Person)
}