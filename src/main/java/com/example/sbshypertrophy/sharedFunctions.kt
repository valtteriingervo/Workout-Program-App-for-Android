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
fun Activity.loadMainMaxes(i: Int): String? {
    val sharedPreferences = getSharedPreferences("sharedMaxes", Context.MODE_PRIVATE)

    val savedSquatMax = sharedPreferences.getString("MAIN_SQUAT_MAX", "140")
    val savedBenchMax = sharedPreferences.getString("MAIN_BENCH_MAX", "100")
    val savedDeadliftMax = sharedPreferences.getString("MAIN_DEADLIFT_MAX", "180")
    val savedOHPMax = sharedPreferences.getString("MAIN_OHP_MAX", "60")

    val savedMaxes = arrayOf(savedSquatMax, savedBenchMax, savedDeadliftMax, savedOHPMax)

    return savedMaxes[i]

    // We probably can't simply pass the view id to this function
    // this function should most likely just return the main maxes in an array
    // If we want to manipulate views directly from the function that should be a separate one?
    // we'll figure this out next
}

fun Activity.loadMainNames(i: Int): String? {
    val sharedPreferences = getSharedPreferences("sharedMaxes", Context.MODE_PRIVATE)
    val savedSquat = sharedPreferences.getString("MAIN_SQUAT", "High Bar Squat")
    val savedBench = sharedPreferences.getString("MAIN_BENCH", "Close Grip Bench")
    val savedDeadlift = sharedPreferences.getString("MAIN_DEADLIFT", "Block Pulls")
    val savedOHP = sharedPreferences.getString("MAIN_OHP", "OHP")

    val savedNames = arrayOf(savedSquat, savedBench, savedDeadlift, savedOHP)

    return savedNames[i]
}

fun Activity.loadAuxNames(i: Int): String? {
    val sharedPreferences = getSharedPreferences("sharedMaxes", Context.MODE_PRIVATE)

    val savedSquatAux1Name = sharedPreferences.getString("AUX1_SQUAT", "Leg Press")
    val savedSquatAux2Name = sharedPreferences.getString("AUX2_SQUAT", "Hack Squat")

    val savedBenchAux1Name = sharedPreferences.getString("AUX1_BENCH", "Incline Bench")
    val savedBenchAux2Name = sharedPreferences.getString("AUX2_BENCH", "DB Bench")

    val savedDeadliftAuxName = sharedPreferences.getString("AUX_DEADLIFT", "RDL")

    val savedOHPAuxName = sharedPreferences.getString("AUX_OHP", "DB OHP")

    val savedAuxNames = arrayOf(savedSquatAux1Name, savedSquatAux2Name, savedBenchAux1Name,
        savedBenchAux2Name, savedDeadliftAuxName, savedOHPAuxName)

    return savedAuxNames[i]


}

fun Activity.loadAuxMaxes(@IntRange(from = 0, to = 5) i: Int): String? {
    val sharedPreferences = getSharedPreferences("sharedMaxes", Context.MODE_PRIVATE)

    val savedSquatAux1Max = sharedPreferences.getString("AUX1_SQUAT_MAX", "140")
    val savedSquatAux2Max = sharedPreferences.getString("AUX2_SQUAT_MAX", "140")

    val savedBenchAux1Max = sharedPreferences.getString("AUX1_BENCH_MAX", "100")
    val savedBenchAux2Max = sharedPreferences.getString("AUX2_BENCH_MAX", "100")

    val savedDeadliftAuxMax = sharedPreferences.getString("AUX_DEADLIFT_MAX", "180")

    val savedOHPAuxMax = sharedPreferences.getString("AUX_OHP_MAX", "60")


    val savedAuxMaxes = arrayOf(savedSquatAux1Max, savedSquatAux2Max, savedBenchAux1Max,
        savedBenchAux2Max, savedDeadliftAuxMax, savedOHPAuxMax)

    return savedAuxMaxes[i]
}

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