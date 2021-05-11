package com.onix.internship.survay.login

import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.onix.internship.survay.database.UsersDao
import com.onix.internship.survay.events.SingleLiveEvent
import com.onix.internship.survay.start.StartFragmentDirections
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.math.BigInteger
import java.security.MessageDigest

class LoginViewModel(val database: UsersDao) : ViewModel(), Observable {
    private val _navigationLiveEvent = SingleLiveEvent<NavDirections>()
    val navigationLiveEvent: LiveData<NavDirections> = _navigationLiveEvent

    @Bindable
    val userLogin = MutableLiveData<String>()

    @Bindable
    val userPassword = MutableLiveData<String>()

    private val _errorLogin = MutableLiveData<Boolean>()
    val errorLogin: LiveData<Boolean> = _errorLogin

    private val _errorPassword = MutableLiveData<Boolean>()
    val errorPassword: LiveData<Boolean> = _errorPassword

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        userLogin.value = ""
        userPassword.value = ""
    }

    fun connect(){
        if (!isError()){
            uiScope.launch {
                val usersNames = database.getUserLogin(userLogin.value!!)
                if (usersNames != null) {
                    Log.i("MYTAG", usersNames.login)
                } else{
                    Log.i("MYTAG", "NULL")
                }
                if (usersNames != null) {
                    if(usersNames.password == md5(userPassword.value!!)){
                        userLogin.value = ""
                        userPassword.value = ""
                        _navigationLiveEvent.value = StartFragmentDirections.actionStartFragmentSelf()
                    }else{
                        _errorPassword.value = true
                    }
                } else {
                    _errorLogin.value = true
                }
            }

        }
    }

    private fun md5(input:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    private fun isError(): Boolean {
        _errorLogin.value = userLogin.value.toString().isEmpty()
        _errorPassword.value = userPassword.value.toString().isEmpty()

        return _errorLogin.value!! || _errorPassword.value!!
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}