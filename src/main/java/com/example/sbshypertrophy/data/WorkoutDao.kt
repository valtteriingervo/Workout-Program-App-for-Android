package com.example.sbshypertrophy.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WorkoutDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWorkout(workout: Workout)

    @Query("SELECT * FROM workout_table")
    fun readAllData(): LiveData<List<Workout>>
}