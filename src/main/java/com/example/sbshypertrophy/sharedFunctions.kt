package com.example.sbshypertrophy

import android.app.Activity
import android.content.Context
import androidx.annotation.IntRange
import kotlin.math.roundToInt

// This file is for creating functions used throughout the app in many activities
// this is so that changing these functions is done simply through this one file.
// For example in all of the workouts we need to load some main movement name and max,
// and some auxiliary name and max.

// Extension function on Activity class (we don't need to pass Context parameter)
// to be used only in Activities
// This way we can call it in Activities without passing any parameters
// such as: loadMainMaxes


// This is to simulate the 'mround' function found on Excel and Google Sheets
fun Activity.mround(value: Double, factor: Double): Double {
    return ((value / factor).roundToInt() * factor)
}

fun isMovementMain(dayNumber: Int, movementOrder: Int): Boolean {
    return if (movementOrder == 1) {
        // The only case where the first movement isn't a main movement is one day 5,
        // Thus 5 != 5 would be false, otherwise this is true
        dayNumber != 5
    }
    else {
        false
    }
}

// Returns the reps per normal set based on the week and movement type (main or aux)
fun Activity.repsPerNormalSet(weekNumber: Int, dayNumber: Int, movementOrder: Int): Int {
    val mainRepsMap = mapOf(1 to 10, 2 to 9, 3 to 8, 4 to 9, 5 to 8, 6 to 7)
    val auxRepsMap = mapOf(1 to 12, 2 to 11, 3 to 10, 4 to 11, 5 to 10, 6 to 9)

    // Return 1 rep if the function gets a wonky Week+Day input
    return if (isMovementMain(dayNumber, movementOrder)) {
        mainRepsMap[weekNumber] ?: 1
    }
    else {
        auxRepsMap[weekNumber] ?: 1
    }
}

// Returns the rep out target on the week and movement type (main or aux)
fun Activity.repOutTarget(weekNumber: Int, dayNumber: Int, movementOrder: Int): Int {
    val mainRepsMap = mapOf(1 to 12, 2 to 11, 3 to 10, 4 to 11, 5 to 10, 6 to 9)
    val auxRepsMap = mapOf(1 to 15, 2 to 13, 3 to 12, 4 to 13, 5 to 12, 6 to 11)

    // Return 2 if the function gets a wonky Week+Day input
    // (such as Week 31, Day 999)
    return if (isMovementMain(dayNumber, movementOrder)) {
        mainRepsMap[weekNumber] ?: 2
    }
    else {
        auxRepsMap[weekNumber] ?: 2
    }
}

// Returns the intensity to be used based the week and movement type (main or aux)
fun intensity(weekNumber: Int, main: Boolean): Double? {
    val mainIntensityMap = mapOf(1 to 0.70, 2 to 0.725, 3 to 0.75, 4 to 0.725, 5 to 0.75, 6 to 0.775)
    val auxIntensityMap = mapOf(1 to 0.65, 2 to 0.675, 3 to 0.70, 4 to 0.675, 5 to 0.70, 6 to 0.725)
    return if (main) {
        mainIntensityMap[weekNumber]
    }
    else {
        auxIntensityMap[weekNumber]
    }
}

// Returns the weight to be used based on function parameters and week
// movementType => e.g. AUX1_SQUAT_MAX, MAIN_BENCH_MAX
fun Activity.movementWeight(weekNumber: Int, dayNumber: Int, movementOrder: Int, rounding: Double): Double {
    // Map different days to their movement maxes
    val firstMovements = mapOf(1 to "MAIN_SQUAT_MAX", 2 to "MAIN_BENCH_MAX", 3 to "MAIN_DEADLIFT_MAX", 4 to "MAIN_OHP_MAX", 5 to "AUX2_BENCH_MAX")
    val secondMovements = mapOf(1 to "AUX_OHP_MAX", 2 to "AUX1_SQUAT_MAX", 3 to "AUX1_BENCH_MAX", 4 to "AUX2_SQUAT_MAX", 5 to "AUX_DEADLIFT_MAX")

    // Load up the shared preferences
    val sharedPreferences = getSharedPreferences("sharedMaxes", Context.MODE_PRIVATE)

    // Figure out movements name to get the max from shared preferences
    val movNameMax = if (movementOrder == 1) {
        firstMovements[dayNumber] ?: "MAIN_SQUAT_MAX"
    }
    else {
        secondMovements[dayNumber] ?: "MAIN_BENCH_MAX"
    }

    // Fetch the max with movNameMax
    val movMax = sharedPreferences.getString(movNameMax, "100") ?: "110"
    val movMaxInt = Integer.parseInt(movMax)
    val movMaxDouble = movMaxInt.toDouble()

    // Now calculate the weight with intensity
    // We need to figure out which are the main lifts first with dictionary
    val mainDict = mapOf("MAIN_SQUAT_MAX" to true, "MAIN_BENCH_MAX" to true, "MAIN_DEADLIFT_MAX" to true, "MAIN_OHP_MAX" to true,
                    "AUX1_SQUAT_MAX" to false, "AUX2_SQUAT_MAX" to false, "AUX1_BENCH_MAX" to false, "AUX2_BENCH_MAX" to false,
                    "AUX_DEADLIFT_MAX" to false, "AUX_OHP_MAX" to false)
    val mainOrNot = mainDict[movNameMax] ?: true
    val movIns = intensity(weekNumber, mainOrNot) ?: 0.70

    return mround(movMaxDouble * movIns, rounding)
}

// Returns the movement name of the type (such as AUX_DEADLIFT) to be displayed on the activity
fun Activity.movementName(@IntRange(from = 1, to = 5) dayNumber: Int, @IntRange(from = 1, to = 2) movementOrder: Int): String {

    // Maps the movements of different days to their generic movement types
    // e.g. Day2 Movement 2 -> AUX1_SQUAT
    val firstMovements = mapOf(1 to "MAIN_SQUAT", 2 to "MAIN_BENCH", 3 to "MAIN_DEADLIFT", 4 to "MAIN_OHP", 5 to "AUX2_BENCH")
    val secondMovements = mapOf(1 to "AUX_OHP", 2 to "AUX1_SQUAT", 3 to "AUX1_BENCH", 4 to "AUX2_SQUAT", 5 to "AUX_DEADLIFT")


    val movementType = if (movementOrder == 1) {
        // ?: is called the Elvis operator => You can use it instead of writing a complete if expression
        // If the expression to the left of ?: is not null, the Elvis operator returns it, otherwise it returns the expression to the right
        firstMovements[dayNumber] ?: "MAIN_SQUAT"
    }
    else {
        secondMovements[dayNumber] ?: "MAIN_SQUAT"
    }

    val sharedPreferences = getSharedPreferences("sharedMaxes", Context.MODE_PRIVATE)

    // Here we use the Elvis operator again
    return sharedPreferences.getString(movementType, "Default Movement Name") ?: "Another Default Movement Name"

}

// Function for fetching the movement type (mainSquat, aux2Bench, mainDeadlift etc.)
// Needed to create entries in the Workout-table
fun Activity.getMovementType(day: Int, movementOrder: Int): String {
    val firstMovTypes = mapOf(1 to "mainSquat", 2 to "mainBench", 3 to "mainDeadlift", 4 to "mainOHP", 5 to "aux2Bench")
    val secondMovTypes = mapOf(1 to "auxOHP", 2 to "aux1Squat", 3 to "aux1Bench", 4 to "aux2Squat", 5 to "auxDeadlift")

    return if (movementOrder == 1) {
        // ?: is called the Elvis operator => You can use it instead of writing a complete if expression
        // If the expression to the left of ?: is not null, the Elvis operator returns it, otherwise it returns the expression to the right
        firstMovTypes[day] ?: "mainSquat"
    }
    else {
        secondMovTypes[day] ?: "auxOHP"
    }
}