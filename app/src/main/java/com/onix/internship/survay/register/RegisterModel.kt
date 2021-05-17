package com.onix.internship.survay.register

import com.onix.internship.survay.database.Users
import com.onix.internship.survay.database.security.md5
import com.onix.internship.survay.errors.ErrorStates

data class RegisterModel(
    var firstName: String = "",
    var lastName: String = "",
    var userLogin: String = "",
    var userPassword: String = "",
    var userPasswordConfirmation: String = ""
) {
    fun firstNameIsEmpty(): ErrorStates {
        return if (firstName.isEmpty()) {
            ErrorStates.EMPTY_FIELD
        } else {
            ErrorStates.NONE
        }
    }

    fun lastNameIsEmpty(): ErrorStates {
        return if (lastName.isEmpty()) {
            ErrorStates.EMPTY_FIELD
        } else {
            ErrorStates.NONE
        }
    }

    fun loginIsEmpty(): ErrorStates {
        return if (userLogin.isEmpty()) {
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

    fun passwordIsCorrect(): ErrorStates {
        return if (!userPasswordConfirmation.equals(userPassword)) {
            ErrorStates.PASSWORDS_MISMATCH
        } else {
            ErrorStates.NONE
        }
    }

    fun fieldsIsCorrect(): Boolean {
        return firstName.isNotEmpty() && lastName.isNotEmpty() && userLogin.isNotEmpty() ||
                userPassword.isNotEmpty() && userPassword.isNotEmpty() &&
                userPasswordConfirmation == userPassword
    }

    fun createUser(): Users {
        return Users(
            0,
            firstName = firstName,
            lastName = lastName,
            login = userLogin,
            password = md5(userPassword),
            role = 0
        )
    }
}
