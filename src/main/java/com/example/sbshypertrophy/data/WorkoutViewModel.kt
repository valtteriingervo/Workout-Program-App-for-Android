package com.example.sbshypertrophy.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkoutViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Workout>>
    private val repository: appRepository

    init {
        val workoutDao = appDatabase.getDatabase(application).WorkoutDao()
        repository = appRepository(workoutDao)
        readAllData = repository.readAllData
    }

    fun addWorkout(workout: Workout) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addWorkout(workout)
        }
    }
}