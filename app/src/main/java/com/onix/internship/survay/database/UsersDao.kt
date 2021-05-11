package com.onix.internship.survay.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsersDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<Users>>

    @Insert
    suspend fun insert(users: Users)

    @Query("SELECT * FROM users WHERE login LIKE :login")
    fun getUserLogin(login: String): Users?

    @Query("SELECT * FROM users WHERE id LIKE :id")
    fun getUserById(id: Int): Users

}