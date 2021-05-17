package com.onix.internship.survay.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.onix.internship.survay.database.TestAppDatabase
import com.onix.internship.survay.errors.ErrorStates
import com.onix.internship.survay.events.SingleLiveEvent
import com.onix.internship.survay.start.StartFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(val database: TestAppDatabase) : ViewModel() {

    val model = RegisterModel()

    private val _navigationLiveEvent = SingleLiveEvent<NavDirections>()
    val navigationLiveEvent: LiveData<NavDirections> = _navigationLiveEvent

    private val _errorFirstName = MutableLiveData(ErrorStates.NONE)
    val errorFirstName: LiveData<ErrorStates> = _errorFirstName

    private val _errorLastName = MutableLiveData(ErrorStates.NONE)
    val errorLastName: LiveData<ErrorStates> = _errorLastName

    private val _errorLogin = MutableLiveData(ErrorStates.NONE)
    val errorLogin: LiveData<ErrorStates> = _errorLogin

    private val _errorPassword = MutableLiveData(ErrorStates.NONE)
    val errorPassword: LiveData<ErrorStates> = _errorPassword

    private val _errorPasswordConfirmation = MutableLiveData(ErrorStates.NONE)
    val errorPasswordConfirmation: LiveData<ErrorStates> = _errorPasswordConfirmation

    fun addUser() {
        model.apply {
            _errorFirstName.value = firstNameIsEmpty()
            _errorLastName.value = lastNameIsEmpty()
            _errorLogin.value = loginIsEmpty()
            _errorPassword.value = passwordIsEmpty()
            _errorPasswordConfirmation.value = passwordIsCorrect()
            if (fieldsIsCorrect()) {
                viewModelScope.launch(Dispatchers.IO) {
                    val isUser = database.usersDao.getUserLogin(userLogin)
                    if (isUser.isNotEmpty()) {
                        _errorLogin.postValue(ErrorStates.ALREADY_IN_USE)
                    } else {
                        val user = createUser()
                        database.usersDao.insert(user)
                        _navigationLiveEvent.postValue(StartFragmentDirections.actionStartFragmentSelf())
                    }
                }
            }
        }
    }
}