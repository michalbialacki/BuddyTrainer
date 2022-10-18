package com.kkp.buddytrainer.data.network

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kkp.buddytrainer.domain.model.Person

@Database(
    entities = [Person::class],
    version = 1,
    exportSchema = false
)
abstract class PersonDb() : RoomDatabase(){
    abstract fun personDAO() : PersonDAO
}