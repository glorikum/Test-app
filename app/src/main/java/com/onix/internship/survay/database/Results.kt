package com.onix.internship.survay.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "results")
data class Results(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "score") val score: Double,
    @ColumnInfo(name = "user_id") val user_id: Int,
    @ColumnInfo(name = "test_id") val test_id: Int
)
