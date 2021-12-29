package com.example.sbshypertrophy.data

import androidx.lifecycle.LiveData

class appRepository(private val workoutDao: WorkoutDao) {
    val readAllData: LiveData<List<Workout>> = workoutDao.readAllData()

    suspend fun addWorkout(workout: Workout) {
        workoutDao.addWorkout(workout)
    }
}