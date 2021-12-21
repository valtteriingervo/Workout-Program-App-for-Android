package com.example.sbshypertrophy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DisplayWorkout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_workout)

        // Fetch the week and day number passed
        // Fetch the chosen week number

        val userWeekNumber = intent.getIntExtra("weekNumber", 5)

        val userDayNumber = intent.getIntExtra("dayNumber", 6)


        // THESE WORK
        // Fetch the movement names, first movement 1
        val mov1name = movementName(userDayNumber, 1)
        // Then movement 2 name
        val mov2name = movementName(userDayNumber, 2)

        findViewById<TextView>(R.id.DisplayWorkout_movement1).text = mov1name
        findViewById<TextView>(R.id.DisplayWorkout_movement2).text = mov2name

        val roundingAmount = 2.5

        // THESE WORK NOW
        val mov1weight = movementWeight(userWeekNumber, userDayNumber, 1, roundingAmount)
        val mov2weight = movementWeight(userWeekNumber, userDayNumber, 2, roundingAmount)

        findViewById<TextView>(R.id.DisplayWorkout_movement1Weight).text = mov1weight.toString()
        findViewById<TextView>(R.id.DisplayWorkout_movement2Weight).text = mov2weight.toString()

        val mov1reps = repsPerNormalSet(userWeekNumber, userDayNumber, 1)
        val mov2reps = repsPerNormalSet(userWeekNumber, userDayNumber, 2)

        findViewById<TextView>(R.id.DisplayWorkout_movement1Reps).text = mov1reps.toString()
        findViewById<TextView>(R.id.DisplayWorkout_movement2Reps).text = mov2reps.toString()

        val mov1repOut = repOutTarget(userWeekNumber, userDayNumber, 1)
        val mov2repOut = repOutTarget(userWeekNumber, userDayNumber, 2)

        findViewById<TextView>(R.id.DisplayWorkout_movement1RepOutTarget).text = mov1repOut.toString()
        findViewById<TextView>(R.id.DisplayWorkout_movement2RepOutTarget).text = mov2repOut.toString()

        // To-do
        // Implement last set reps input field
        // Implement submit workout field
    }
}