package com.onix.internship.survay.register

import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.onix.internship.survay.database.Users
import com.onix.internship.survay.database.UsersDao
import com.onix.internship.survay.events.SingleLiveEvent
import com.onix.internship.survay.start.StartFragmentDirections
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.math.BigInteger
import java.security.MessageDigest

class RegisterViewModel(val database: UsersDao) : ViewModel(), Observable {

    private val _navigationLiveEvent = SingleLiveEvent<NavDirections>()
    val navigationLiveEvent: LiveData<NavDirections> = _navigationLiveEvent

    @Bindable
    val firstName = MutableLiveData<String>()

    @Bindable
    val lastName = MutableLiveData<String>()

    @Bindable
    val userLogin = MutableLiveData<String>()

    @Bindable
    val userPassword = MutableLiveData<String>()

    @Bindable
    val userPasswordConfirmation = MutableLiveData<String>()

    private val _errorFirstName = MutableLiveData<Boolean>()
    val errorFirstName: LiveData<Boolean> = _errorFirstName

    private val _errorLastName = MutableLiveData<Boolean>()
    val errorLastName: LiveData<Boolean> = _errorLastName

    private val _errorLogin = MutableLiveData<Boolean>()
    val errorLogin: LiveData<Boolean> = _errorLogin

    private val _errorPassword = MutableLiveData<Boolean>()
    val errorPassword: LiveData<Boolean> = _errorPassword

    private val _errorPasswordConfirmation = MutableLiveData<Boolean>()
    val errorPasswordConfirmation: LiveData<Boolean> = _errorPasswordConfirmation

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        firstName.value = ""
        lastName.value = ""
        userLogin.value = ""
        userPassword.value = ""
        userPasswordConfirmation.value = ""
    }

    fun addUser() {
        Log.i("MYTAG", "++++++++")
        if (!isError()) {
            uiScope.launch {
                insert(
                    Users(
                        0,
                        firstName.value!!,
                        lastName.value!!,
                        userLogin.value!!,
                        md5(userPassword.value!!),
                        getRole()
                    )
                )
            }
            _navigationLiveEvent.value = StartFragmentDirections.actionStartFragmentSelf()
        }
    }

    private fun getRole(): Int  {

        return 1
    }

    private fun md5(input:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    private fun isError(): Boolean {
        _errorFirstName.value = firstName.value.toString().isEmpty()
        _errorLastName.value = lastName.value.toString().isEmpty()
        _errorLogin.value = userLogin.value.toString().isEmpty()
        _errorPassword.value = userPassword.value.toString().isEmpty()
        _errorPasswordConfirmation.value =
            !userPasswordConfirmation.value.equals(userPassword.value)

        return _errorFirstName.value!! || _errorLastName.value!! || _errorLogin.value!! || _errorPassword.value!! || _errorPasswordConfirmation.value!!
    }

    private fun insert(user: Users): Job = viewModelScope.launch {
        database.insert(user)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}