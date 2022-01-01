package com.example.sbshypertrophy

import android.app.Activity
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    // Create dummy Activity to test the extended functions
    private val testAct: Activity = Activity()

    @Test
    fun getMovementType_isCorrect() {
        assertEquals("mainSquat", testAct.getMovementType(1, 1))
        assertEquals("auxOHP", testAct.getMovementType(1, 2))

        assertEquals("mainBench", testAct.getMovementType(2, 1))
        assertEquals("aux1Squat", testAct.getMovementType(2, 2))

        assertEquals("mainDeadlift", testAct.getMovementType(3, 1))
        assertEquals("aux1Bench", testAct.getMovementType(3, 2))

    }
}