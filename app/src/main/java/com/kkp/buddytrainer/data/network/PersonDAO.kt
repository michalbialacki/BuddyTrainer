package com.kkp.buddytrainer.data.network

import androidx.room.*
import com.kkp.buddytrainer.core.Constants.PERSON_TABLE
import com.kkp.buddytrainer.domain.model.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDAO {
    @Query("SELECT * FROM $PERSON_TABLE ORDER BY id ASC")
    fun getBuddies() : Flow<List<Person>>

    @Query("SELECT * FROM $PERSON_TABLE WHERE id = :id")
    fun getPerson(id : Int) : Flow<Person>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addBuddy(buddy: Person)

    @Delete
    fun deleteBuddy(buddy: Person)
}