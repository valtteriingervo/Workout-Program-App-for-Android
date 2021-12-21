package com.example.sbshypertrophy

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AuxiliaryLifts : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auxiliary_lifts)

        // A button to take us back to the main lifts screen
        val button = findViewById<Button>(R.id.week1Workouts_backButton)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // A button to take us to the screen where auxiliary lift names and maxes are displayed
        val nextButton = findViewById<Button>(R.id.nextButton)
        nextButton.setOnClickListener {
            val intent = Intent(this, ShowAuxiliaryLiftsValues::class.java)
            startActivity(intent)
        }

        // Button to submit the aux lift maxes
        // To-do: Test if this works
        val submitButton = findViewById<Button>(R.id.submitAuxMaxesButton)
        submitButton.setOnClickListener {
            saveAuxMaxes()
            saveAuxNames()
            // After submitting new maxes, you need to load them to display on screen
            //loadMainMaxes()
        }
    }

    // Private function to save the inserted auxiliary movement maxes
    private fun saveAuxMaxes() {
        val insertedAux1SquatMax = findViewById<EditText>(R.id.aux1SquatMax)
        val aux1SquatMaxToString = insertedAux1SquatMax.text.toString()
        val insertedAux2SquatMax = findViewById<EditText>(R.id.aux2SquatMax)
        val aux2SquatMaxToString = insertedAux2SquatMax.text.toString()

        val insertedAux1BenchMax = findViewById<EditText>(R.id.aux1BenchMax)
        val aux1BenchMaxToString = insertedAux1BenchMax.text.toString()
        val insertedAux2BenchMax = findViewById<EditText>(R.id.aux2BenchMax)
        val aux2BenchMaxToString = insertedAux2BenchMax.text.toString()

        val insertedAuxDeadliftMax = findViewById<EditText>(R.id.auxDeadliftMax)
        val auxDeadliftMaxToString = insertedAuxDeadliftMax.text.toString()

        val insertedAuxOHPMax = findViewById<EditText>(R.id.auxOHPMax)
        val auxOHPMaxToString = insertedAuxOHPMax.text.toString()


        val sharedPreferences = getSharedPreferences("sharedMaxes", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("AUX1_SQUAT_MAX", aux1SquatMaxToString)
            putString("AUX2_SQUAT_MAX", aux2SquatMaxToString)

            putString("AUX1_BENCH_MAX", aux1BenchMaxToString)
            putString("AUX2_BENCH_MAX", aux2BenchMaxToString)

            putString("AUX_DEADLIFT_MAX", auxDeadliftMaxToString)

            putString("AUX_OHP_MAX", auxOHPMaxToString)

        }.apply()

        Toast.makeText(this, "Auxiliary Maxes saved", Toast.LENGTH_SHORT).show()
    }

    private fun saveAuxNames() {
        val insertedAux1Squat = findViewById<EditText>(R.id.aux1Squat)
        val aux1SquatToString = insertedAux1Squat.text.toString()
        val insertedAux2Squat = findViewById<EditText>(R.id.aux2Squat)
        val aux2SquatToString = insertedAux2Squat.text.toString()

        val insertedAux1Bench = findViewById<EditText>(R.id.aux1Bench)
        val aux1BenchToString = insertedAux1Bench.text.toString()
        val insertedAux2Bench = findViewById<EditText>(R.id.aux2Bench)
        val aux2BenchToString = insertedAux2Bench.text.toString()

        val insertedAuxDeadlift = findViewById<EditText>(R.id.auxDeadlift)
        val auxDeadliftToString = insertedAuxDeadlift.text.toString()

        val insertedAuxOHP = findViewById<EditText>(R.id.auxOHP)
        val auxOHPToString = insertedAuxOHP.text.toString()


        val sharedPreferences = getSharedPreferences("sharedMaxes", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("AUX1_SQUAT", aux1SquatToString)
            putString("AUX2_SQUAT", aux2SquatToString)

            putString("AUX1_BENCH", aux1BenchToString)
            putString("AUX2_BENCH", aux2BenchToString)

            putString("AUX_DEADLIFT", auxDeadliftToString)

            putString("AUX_OHP", auxOHPToString)

        }.apply()

        Toast.makeText(this, "Auxiliary Names saved", Toast.LENGTH_SHORT).show()
    }

}