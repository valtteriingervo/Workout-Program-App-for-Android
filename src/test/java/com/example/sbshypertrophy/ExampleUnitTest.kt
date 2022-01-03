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

    @Test
    fun isMovementMain_isCorrect() {
        assertEquals(true, testAct.isMovementMain(1, 1))
        assertEquals(false, testAct.isMovementMain(1, 2))

        assertEquals(true, testAct.isMovementMain(2, 1))
        assertEquals(false, testAct.isMovementMain(2, 2))

        assertEquals(false, testAct.isMovementMain(5, 1))
        assertEquals(false, testAct.isMovementMain(5, 2))
    }

    @Test
    fun repsPerNormalSet_isCorrect() {
        assertEquals(10, testAct.repsPerNormalSet(1, 1, 1))
        assertEquals(12, testAct.repsPerNormalSet(1, 1, 2))

        assertEquals(12, testAct.repsPerNormalSet(1, 5, 1))
        assertEquals(12, testAct.repsPerNormalSet(1, 5, 2))

        assertEquals(8, testAct.repsPerNormalSet(3, 1, 1))
        assertEquals(10, testAct.repsPerNormalSet(3, 1, 2))

        assertEquals(7, testAct.repsPerNormalSet(6, 3, 1))
        assertEquals(9, testAct.repsPerNormalSet(6, 3, 2))
    }

    @Test
    fun repOutTarget_isCorrect() {
        assertEquals(12, testAct.repOutTarget(1, 1, 1))
        assertEquals(15, testAct.repOutTarget(1, 1, 2))

        assertEquals(15, testAct.repOutTarget(1, 5, 1))
        assertEquals(15, testAct.repOutTarget(1, 5, 2))

        assertEquals(10, testAct.repOutTarget(3, 1, 1))
        assertEquals(12, testAct.repOutTarget(3, 1, 2))

        assertEquals(9, testAct.repOutTarget(6, 3, 1))
        assertEquals(11, testAct.repOutTarget(6, 3, 2))
    }

    @Test
    fun intensity_isCorrect() {
        assertEquals(0.70, testAct.intensity(1, true))
        assertEquals(0.65, testAct.intensity(1, false))

        assertEquals(0.75, testAct.intensity(3, true))
        assertEquals(0.70, testAct.intensity(3, false))

        assertEquals(0.775, testAct.intensity(6, true))
        assertEquals(0.725, testAct.intensity(6, false))
    }

    @Test
    fun mround_isCorrect() {
        val delta = 0.1
        assertEquals(102.5, testAct.mround(101.73, 2.5), delta)
        assertEquals(100.0, testAct.mround(100.73, 2.5), delta)
        assertEquals(92.5, testAct.mround(91.32, 2.5), delta)

        assertEquals(91.0, testAct.mround(91.32, 1.0), delta)
        assertEquals(66.5, testAct.mround(66.43, 0.5), delta)
    }
}