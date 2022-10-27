package com.kkp.buddytrainer.domain.model

data class Exercise(
    val reps : ArrayList<String>? = arrayListOf<String>(""),
    val multi : ArrayList<Float>? = arrayListOf<Float>(0f),
    val name : String? = "",

    )
