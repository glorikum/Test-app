package com.onix.internship.survay.bindingadapter

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.onix.internship.survay.R
import com.onix.internship.survay.errors.ErrorStates

@BindingAdapter("errorMessage")
fun TextInputLayout.errorMessage(errorState: ErrorStates) {
        error = when (errorState) {
            ErrorStates.NONE -> ""
            ErrorStates.EMPTY_FIELD -> context.getString(R.string.field_is_empty)
            ErrorStates.PASSWORDS_MISMATCH -> context.getString(R.string.password_mismatch)
            ErrorStates.INVALID_PASSWORD -> context.getString(R.string.invalid_password)
            ErrorStates.NO_SUCH_USER -> context.getString(R.string.no_user)
            ErrorStates.ALREADY_IN_USE -> context.getString(R.string.already_in_use)
        }
}