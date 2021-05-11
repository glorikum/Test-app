package com.onix.internship.survay.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onix.internship.survay.database.UsersDao

class RegisterViewModelFactory(
    private val database: UsersDao
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}