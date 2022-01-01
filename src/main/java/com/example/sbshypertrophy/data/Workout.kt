package com.example.sbshypertrophy.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_table", primaryKeys = ["week", "day"])
data class Workout (
    val week: Int,
    val day: Int,
    // Such as mainSquat or aux1Squat
    val mov1Type: String,
    val mov2Type: String,
    // We need to store workout (or rather week specific)
    // movement training maxes to track how our lifts are progressing
    val mov1tm: Double,
    val mov2tm: Double,
    val mov1FinalSetRepAmount: Int,
    val mov2FinalSetRepAmount: Int,
    )
