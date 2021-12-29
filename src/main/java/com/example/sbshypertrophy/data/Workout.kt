package com.example.sbshypertrophy.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_table")
data class Workout (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val week: Int,
    val day: Int,
    val isComplete: Boolean,
    val mov1TrainingMax: Double,
    val mov2TrainingMax: Double,
    // Such as mainSquat or aux1Squat
    val mov1type: String,
    val mov2type: String
        ){
}