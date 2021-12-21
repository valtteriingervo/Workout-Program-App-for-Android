package com.example.sbshypertrophy

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ShowAuxiliaryLiftsValues : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_auxiliary_lifts_values)

        // Always load the aux names and maxes to display when launching the Activity
        loadAuxNames()
        loadAuxMaxes()

        // A button to take us back to the main lifts screen
        val button = findViewById<Button>(R.id.backButton)
        button.setOnClickListener {
            val intent = Intent(this, AuxiliaryLifts::class.java)
            startActivity(intent)
        }
        
        val nextButton = findViewById<Button>(R.id.showAuxNextButton)
        nextButton.setOnClickListener {
            val intent = Intent(this, WeekSelection::class.java)
            startActivity(intent)
        }
    }

    // Load the names of the auxiliary lifts set on the previous Activity
    private fun loadAuxNames() {
        val sharedPreferences = getSharedPreferences("sharedMaxes", Context.MODE_PRIVATE)

        val savedSquatAux1Name = sharedPreferences.getString("AUX1_SQUAT", "Leg Press")
        val savedSquatAux2Name = sharedPreferences.getString("AUX2_SQUAT", "Hack Squat")

        val savedBenchAux1Name = sharedPreferences.getString("AUX1_BENCH", "Incline Bench")
        val savedBenchAux2Name = sharedPreferences.getString("AUX2_BENCH", "DB Bench")

        val savedDeadliftAuxName = sharedPreferences.getString("AUX_DEADLIFT", "RDL")

        val savedOHPAuxName = sharedPreferences.getString("AUX_OHP", "DB OHP")


        findViewById<TextView>(R.id.squatAux1Name).text = savedSquatAux1Name
        findViewById<TextView>(R.id.squatAux2Name).text = savedSquatAux2Name

        findViewById<TextView>(R.id.benchAux1Name).text = savedBenchAux1Name
        findViewById<TextView>(R.id.benchAux2Name).text = savedBenchAux2Name

        findViewById<TextView>(R.id.deadliftAuxName).text = savedDeadliftAuxName

        findViewById<TextView>(R.id.OHPAuxName).text = savedOHPAuxName
    }

    private fun loadAuxMaxes() {
        val sharedPreferences = getSharedPreferences("sharedMaxes", Context.MODE_PRIVATE)

        val savedSquatAux1Max = sharedPreferences.getString("AUX1_SQUAT_MAX", "140")
        val savedSquatAux2Max = sharedPreferences.getString("AUX2_SQUAT_MAX", "140")

        val savedBenchAux1Max = sharedPreferences.getString("AUX1_BENCH_MAX", "100")
        val savedBenchAux2Max = sharedPreferences.getString("AUX2_BENCH_MAX", "100")

        val savedDeadliftAuxMax = sharedPreferences.getString("AUX_DEADLIFT_MAX", "180")

        val savedOHPAuxMax = sharedPreferences.getString("AUX_OHP_MAX", "60")


        findViewById<TextView>(R.id.squatAux1Max).text = savedSquatAux1Max
        findViewById<TextView>(R.id.squatAux2Max).text = savedSquatAux2Max

        findViewById<TextView>(R.id.benchAux1Max).text = savedBenchAux1Max
        findViewById<TextView>(R.id.benchAux2Max).text = savedBenchAux2Max

        findViewById<TextView>(R.id.deadliftAuxMax).text = savedDeadliftAuxMax

        findViewById<TextView>(R.id.OHPAuxMax).text = savedOHPAuxMax
    }
}