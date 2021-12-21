package com.example.sbshypertrophy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class WeekSelection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_week_selection)

        // Initiate the list view content, we'll add more weeks later
        val myStringArray = arrayOf("Week 1", "Week 2", "Week 3", "Week 4", "Week 5", "Week 6")
        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, myStringArray)

        val listView = findViewById<ListView>(R.id.week1Workouts_listView)
        listView.adapter = adapter

        // Have the list view be a portal to the different weeks when clicked
        listView.setOnItemClickListener {adapterView, view, i, l ->
            //Toast.makeText(this, "Item Selected" + myStringArray[i], Toast.LENGTH_SHORT).show()
            val weekNumber = when (i) {
                0 -> 1
                1 -> 2
                2 -> 3
                3 -> 4
                4 -> 5
                5 -> 6
                else -> 1
            }
            val intent = Intent(this, WorkoutSelection::class.java)
            intent.putExtra("weekNumber", weekNumber)
            startActivity(intent)
        }

        // A button to take us back to the main lifts screen
        val button = findViewById<Button>(R.id.week1Workouts_backButton)
        button.setOnClickListener {
            val intent = Intent(this, ShowAuxiliaryLiftsValues::class.java)
            startActivity(intent)
        }

        }


    }