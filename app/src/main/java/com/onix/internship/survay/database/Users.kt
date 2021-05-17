package com.onix.internship.survay.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class Users(
    @PrimaryKey(autoGenerate = true) var usersId: Long = 0L,
    @ColumnInfo(name = "first_name") var firstName: String = "",
    @ColumnInfo(name = "last_name") var lastName: String = "",
    @ColumnInfo(name = "login") var login: String = "",
    @ColumnInfo(name = "password") val password: String = "",
    @ColumnInfo(name = "role") val role: Int = 1
)