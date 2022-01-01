package com.example.sbshypertrophy.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movement_table")
data class Movement(
    @PrimaryKey val movType: String,
    val movName: String,
    val movOriginalMax: Double,
    val movMultiplier: Double
)
