package com.onix.internship.survay.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.onix.internship.survay.database.TestAppDatabase
import com.onix.internship.survay.database.security.md5
import com.onix.internship.survay.errors.ErrorStates
import com.onix.internship.survay.events.SingleLiveEvent
import com.onix.internship.survay.start.StartFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(val database: TestAppDatabase) : ViewModel() {

    val model = LoginModel()

    private val _navigationLiveEvent = SingleLiveEvent<NavDirections>()
    val navigationLiveEvent: LiveData<NavDirections> = _navigationLiveEvent

    private var _errorLogin = MutableLiveData(ErrorStates.NONE)
    var errorLogin: LiveData<ErrorStates> = _errorLogin

    private var _errorPassword = MutableLiveData(ErrorStates.NONE)
    var errorPassword: LiveData<ErrorStates> = _errorPassword

    fun connectButton() {
        model.apply {
            _errorLogin.value = loginIsEmpty()
            _errorPassword.value = passwordIsEmpty()
            if(fieldsIsNotEmpty()){
                viewModelScope.launch(Dispatchers.IO) {
                    val user = database.usersDao.getUserLogin(userLogin)
                    if (user.isNotEmpty()) {
                        if (user[0].password == md5(userPassword)) {
                            _navigationLiveEvent.postValue(StartFragmentDirections.actionStartFragmentToAdminFragment())
                        } else {
                            _errorPassword.postValue(ErrorStates.INVALID_PASSWORD)
                        }
                    } else {
                        _errorLogin.postValue(ErrorStates.NO_SUCH_USER)
                    }
                }
            }
        }
    }
}