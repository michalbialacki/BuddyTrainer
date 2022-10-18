package com.kkp.buddytrainer.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kkp.buddytrainer.core.Constants.PERSON_TABLE

@Entity(tableName = PERSON_TABLE)
data class Person(
    @PrimaryKey(autoGenerate = false)
    var Bench : Float,
    var Squat : Float,
    var Deadlift : Float,
    val Name : String,
    val id : Long
)