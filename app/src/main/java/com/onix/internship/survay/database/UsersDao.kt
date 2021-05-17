package com.onix.internship.survay.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsersDao {

    @Insert
    suspend fun insert(users: Users)

    @Query("SELECT * FROM users ORDER BY usersId ASC")
    suspend fun getAllUsers(): List<Users>

    @Query("SELECT * FROM users WHERE login = :login")
    suspend fun getUserLogin(login: String): List<Users>

}