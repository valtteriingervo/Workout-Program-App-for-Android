package com.example.sbshypertrophy

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.example.sbshypertrophy.data.Workout
import com.example.sbshypertrophy.data.WorkoutViewModel

class DisplayWorkout : AppCompatActivity() {

    private lateinit var mWorkoutViewModel: WorkoutViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_workout)

        // Fetch the week and day number passed
        // Fetch the chosen week number

        mWorkoutViewModel = ViewModelProvider(this)[WorkoutViewModel::class.java]


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

        findViewById<TextView>(R.id.DisplayWorkout_movement1RepOutTarget).text =
            mov1repOut.toString()
        findViewById<TextView>(R.id.DisplayWorkout_movement2RepOutTarget).text =
            mov2repOut.toString()

        // To-do
        // Implement last set reps input field
        // Implement submit workout field
        // This needs to happen only when the user checks that he/she wants to end the workout!
        val endWorkoutCheckBox = findViewById<CheckBox>(R.id.displayWorkout_EndWorkoutCheckBox)

        // Remember to handle the event where there is nothing inserted to "Reps on last set: " field
        // Otherwise the app will throw and NullPointerException and crash
        endWorkoutCheckBox.setOnCheckedChangeListener { buttonView, _ ->
            if (buttonView.isChecked) {
                // We'll pass incorrect values for now just to focus on testing how Room DB system works
                addWorkoutToDatabase(userWeekNumber, userDayNumber, mov1weight,
                mov2weight, mov1name, mov2name, mWorkoutViewModel)
            }
        }




    }

    private fun lastSetRepsEffectOnMaxValue(movDoneReps: Int, movRepOutTarget: Int): Double {
        // Press Ctrl + R for "find and replace" functionality

        // Then we need to find the rep differential for both movements
        // These are stored in mov1repOut and mov2repOut

        // For example if the user hits 11 reps and rep out target is 12
        // then movRepDifferential would be -1 and thus we would reduce the training max
        // similarly if we hit 13 reps when the target is 12, the differential is 1 and we raise the TM
        val movRepDifferential = movDoneReps - movRepOutTarget

        // Return the appropriate value to have an effect on the max
        return when {
            movRepDifferential > 5 -> 1.03
            movRepDifferential == 4 -> 1.02
            movRepDifferential == 3 -> 1.015
            movRepDifferential == 2 -> 1.01
            movRepDifferential == 1 -> 1.005
            movRepDifferential == 0 -> 1.0
            movRepDifferential == -1 -> 0.98
            else -> 0.95
        }
    }

    private fun addWorkoutToDatabase(week: Int, day: Int, mov1tm: Double, mov2tm: Double,
                                     mov1type: String, mov2type: String, wvm: WorkoutViewModel) {
        val mov1RepsOnLastSet =
            findViewById<EditText>(R.id.displayWorkout_mov1_RepsOnLastSetInput)
        val mov1RepsOnLastSetToString = mov1RepsOnLastSet.text.toString()
        val mov1RepsInt = Integer.parseInt(mov1RepsOnLastSetToString)

        //val mov1MaxEffectValue = lastSetRepsEffectOnMaxValue(mov1RepsInt, mov1repOut)

        // The Room library will handle id creation. We just need to pass a '0' in id field
        //val workout = Workout(0, week, day, true, mov1tm, mov2tm, mov1type, mov2type)
        //wvm.addWorkout(workout)
        Toast.makeText(this,"Successfully logged workout", Toast.LENGTH_SHORT).show()
    }

}


