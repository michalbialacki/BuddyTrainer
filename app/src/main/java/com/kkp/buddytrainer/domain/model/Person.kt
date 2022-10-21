package com.kkp.buddytrainer.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kkp.buddytrainer.core.Constants.PERSON_TABLE

@Entity(tableName = PERSON_TABLE)
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 404L,
    var Bench : Float = 100f,
    var Squat : Float = 100f,
    var Deadlift : Float = 100f,
    val Name : String = "",
)