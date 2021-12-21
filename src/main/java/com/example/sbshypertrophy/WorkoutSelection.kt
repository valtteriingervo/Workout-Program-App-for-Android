package com.example.sbshypertrophy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class WorkoutSelection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_selection)

        val selectedWeekNumber = intent.getIntExtra("weekNumber", 1)

        //Toast.makeText(this, "Week number $selectedWeekNumber selected", Toast.LENGTH_SHORT).show()

        val myStringArray = arrayOf("Week $selectedWeekNumber Day 1", "Week $selectedWeekNumber Day 2", "Week $selectedWeekNumber Day 3",
                                    "Week $selectedWeekNumber Day 4", "Week $selectedWeekNumber Day 5")
        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, myStringArray)

        val listView = findViewById<ListView>(R.id.week1Workouts_listView)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, i, _ ->
            //Toast.makeText(this, "Item Selected" + myStringArray[i], Toast.LENGTH_SHORT).show()
            // Week 1 Day 1
            if (i == 0) {
                val intent = Intent(this, DisplayWorkout::class.java)
                val dayNumber = 1
                intent.putExtra("dayNumber", dayNumber)
                intent.putExtra("weekNumber", selectedWeekNumber)
                startActivity(intent)
            }
            // Week 1 Day 2
            if (i == 1) {
                val intent = Intent(this, DisplayWorkout::class.java)
                val dayNumber = 2
                intent.putExtra("dayNumber", dayNumber)
                intent.putExtra("weekNumber", selectedWeekNumber)
                startActivity(intent)
            }
            // Week 1 Day 3
            if (i == 2) {
                val intent = Intent(this, DisplayWorkout::class.java)
                val dayNumber = 3
                intent.putExtra("dayNumber", dayNumber)
                intent.putExtra("weekNumber", selectedWeekNumber)
                startActivity(intent)
            }
            // Week 1 Day 4
            if (i == 3) {
                val intent = Intent(this, DisplayWorkout::class.java)
                val dayNumber = 4
                intent.putExtra("dayNumber", dayNumber)
                intent.putExtra("weekNumber", selectedWeekNumber)
                startActivity(intent)
            }
            // Week 1 Day 5
            if (i == 4) {
                val intent = Intent(this, DisplayWorkout::class.java)
                val dayNumber = 5
                intent.putExtra("dayNumber", dayNumber)
                intent.putExtra("weekNumber", selectedWeekNumber)
                startActivity(intent)
            }
        }

        // A button to take us back to the main lifts screen
        val button = findViewById<Button>(R.id.week1Workouts_backButton)
        button.setOnClickListener {
            val intent = Intent(this, WeekSelection::class.java)
            startActivity(intent)
        }
    }
}