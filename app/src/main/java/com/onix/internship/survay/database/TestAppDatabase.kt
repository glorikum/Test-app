package com.onix.internship.survay.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Users::class, Tests::class, Access::class, Answers::class, Questions::class, Results::class],
    version = 1
)
abstract class TestAppDatabase : RoomDatabase() {
    abstract val usersDao: UsersDao

    companion object {
        @Volatile
        private var INSTANCE: TestAppDatabase? = null

        fun getInstance(context: Context): TestAppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TestAppDatabase::class.java,
                        "user_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}