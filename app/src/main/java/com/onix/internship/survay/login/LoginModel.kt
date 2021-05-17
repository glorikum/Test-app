package com.onix.internship.survay.login

import com.onix.internship.survay.errors.ErrorStates

data class LoginModel(
    var userLogin: String = "",
    var userPassword: String = ""
) {
    fun loginIsEmpty(): ErrorStates {
        return if (userPassword.isEmpty()) {
            ErrorStates.EMPTY_FIELD
        } else {
            ErrorStates.NONE
        }
    }

    fun passwordIsEmpty(): ErrorStates {
        return if (userPassword.isEmpty()) {
            ErrorStates.EMPTY_FIELD
        } else {
            ErrorStates.NONE
        }
    }

    fun fieldsIsNotEmpty(): Boolean {
        return userLogin.isNotEmpty() && userPassword.isNotEmpty()
    }
}
