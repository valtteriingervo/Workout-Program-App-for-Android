package com.example.sbshypertrophy

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.SharedPreferences
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Button to move to the auxiliary lifts screen
        val button = findViewById<Button>(R.id.mainInfoNextButton)
        button.setOnClickListener {
            val intent = Intent(this, AuxiliaryLifts::class.java)
            startActivity(intent)
        }

        // Load the Main Lift Maxes previously set by the user
        // The Default Numbers are 140, 100, 180, 60 if nothing was set
        loadMainMaxes()
        loadMainNames()

        // Button to submit the main lift maxes
        val submitButton = findViewById<Button>(R.id.submitMaxesButton)
        submitButton.setOnClickListener {
            saveMainMaxes()
            saveMainNames()
            // After submitting new maxes, you need to load them to display on screen
            loadMainMaxes()
            loadMainNames()
        }
    }

    // Private function, only accessible in the MainActivity.kt file,
    // for saving main maxes to be used easily in other activities
    private fun saveMainMaxes() {
        val insertedSquatMax = findViewById<EditText>(R.id.mainSquatMax)
        val squatMaxToString = insertedSquatMax.text.toString()

        val insertedBenchMax = findViewById<EditText>(R.id.mainBenchMax)
        val benchMaxToString = insertedBenchMax.text.toString()

        val insertedDeadliftMax = findViewById<EditText>(R.id.mainDeadLiftMax)
        val deadliftMaxToString = insertedDeadliftMax.text.toString()

        val insertedOHPMax = findViewById<EditText>(R.id.mainOHPMax)
        val OHPMaxToString = insertedOHPMax.text.toString()


        val sharedPreferences = getSharedPreferences("sharedMaxes", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("MAIN_SQUAT_MAX", squatMaxToString)
            putString("MAIN_BENCH_MAX", benchMaxToString)
            putString("MAIN_DEADLIFT_MAX", deadliftMaxToString)
            putString("MAIN_OHP_MAX", OHPMaxToString)
        }.apply()

        Toast.makeText(this, "Maxes saved", Toast.LENGTH_SHORT).show()
    }

    private fun saveMainNames() {
        val insertedMain1Squat = findViewById<EditText>(R.id.mainSquat)
        val mainSquatToString = insertedMain1Squat.text.toString()

        val insertedMain1Bench = findViewById<EditText>(R.id.mainBench)
        val mainBenchToString = insertedMain1Bench.text.toString()

        val insertedMainDeadlift = findViewById<EditText>(R.id.mainDeadlift)
        val mainDeadliftToString = insertedMainDeadlift.text.toString()

        val insertedMainOHP = findViewById<EditText>(R.id.mainOHP)
        val mainOHPToString = insertedMainOHP.text.toString()


        val sharedPreferences = getSharedPreferences("sharedMaxes", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("MAIN_SQUAT", mainSquatToString)

            putString("MAIN_BENCH", mainBenchToString)

            putString("MAIN_DEADLIFT", mainDeadliftToString)

            putString("MAIN_OHP", mainOHPToString)

        }.apply()

        Toast.makeText(this, "Main Names saved", Toast.LENGTH_SHORT).show()
    }

    // Private function for loading the main maxes set by the user,
    // This function includes the default values of 140, 100, 180, 60 for S/B/D/OHP
    private fun loadMainMaxes() {
        val sharedPreferences = getSharedPreferences("sharedMaxes", Context.MODE_PRIVATE)
        val savedSquatMax = sharedPreferences.getString("MAIN_SQUAT_MAX", "140")
        val savedBenchMax = sharedPreferences.getString("MAIN_BENCH_MAX", "100")
        val savedDeadliftMax = sharedPreferences.getString("MAIN_DEADLIFT_MAX", "180")
        val savedOHPMax = sharedPreferences.getString("MAIN_OHP_MAX", "60")

        // Load the saved squat main max to the 'display current maxes' text box
        findViewById<TextView>(R.id.displayCurrentMainSquatMax).text = savedSquatMax
        findViewById<TextView>(R.id.displayCurrentMainBenchMax).text = savedBenchMax
        findViewById<TextView>(R.id.displayCurrentMainDeadliftMax).text = savedDeadliftMax
        findViewById<TextView>(R.id.displayCurrentMainOHPMax).text = savedOHPMax
    }

    private fun loadMainNames() {
        val sharedPreferences = getSharedPreferences("sharedMaxes", Context.MODE_PRIVATE)
        val savedSquat = sharedPreferences.getString("MAIN_SQUAT", "High Bar Squat")
        val savedBench = sharedPreferences.getString("MAIN_BENCH", "Close Grip Bench")
        val savedDeadlift = sharedPreferences.getString("MAIN_DEADLIFT", "Block Pulls")
        val savedOHP = sharedPreferences.getString("MAIN_OHP", "OHP")

        // Load the saved squat main  to the 'display current es' text box
        findViewById<TextView>(R.id.squatMainName).text = savedSquat
        findViewById<TextView>(R.id.benchMainName).text = savedBench
        findViewById<TextView>(R.id.deadliftMainName).text = savedDeadlift
        findViewById<TextView>(R.id.OHPMainName).text = savedOHP
    }

}