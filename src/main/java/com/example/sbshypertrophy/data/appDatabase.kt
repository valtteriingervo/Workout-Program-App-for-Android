package com.example.sbshypertrophy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Workout::class, Movement::class], version = 1, exportSchema = false)
abstract class appDatabase: RoomDatabase() {

    abstract fun WorkoutDao(): WorkoutDao
    abstract fun MovementDao(): MovementDao

    companion object {
        @Volatile
        private var INSTANCE: appDatabase? = null

        // Having multiple instances of a database is very costly
        // for performance. With this function we make sure we are always
        // using the same instance for appDatabase
        fun getDatabase(context: Context): appDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    appDatabase::class.java,
                    "workout_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}