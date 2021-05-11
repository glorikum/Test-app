package com.onix.internship.survay.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onix.internship.survay.database.UsersDao

class LoginViewModelFactory(private val database: UsersDao) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}