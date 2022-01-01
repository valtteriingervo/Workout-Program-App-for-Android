package com.example.sbshypertrophy.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovementDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWorkout(workout: Workout)

    @Query("SELECT * FROM movement_table")
    fun readAllData(): LiveData<List<Movement>>

    @Query("SELECT movMultiplier FROM movement_table WHERE movType = :paramMovType")
    fun getMultiplier(paramMovType: String): List<Double>?

    @Query("UPDATE movement_table SET movMultiplier = (movMultiplier * :paramMultiplier) WHERE movType = :paramMovType")
    fun updateMultiplier(paramMultiplier: Double, paramMovType: String)

    @Query("SELECT movOriginalMax FROM movement_table WHERE movType = :paramMovType")
    fun getOriginalMax(paramMovType: String): LiveData<List<Double>>
}