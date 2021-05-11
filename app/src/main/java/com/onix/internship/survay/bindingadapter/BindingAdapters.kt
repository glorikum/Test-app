package com.onix.internship.survay.bindingadapter

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.onix.internship.survay.R
import kotlinx.android.synthetic.main.login_fragment.view.*
import kotlinx.android.synthetic.main.register_fragment.view.*

@BindingAdapter("errorMessage")
fun TextInputLayout.errorMessage(errorState: Boolean) {
    val id = this
    error = if (errorState) {
        when (id) {
            editPasswordConfirmationLayout -> resources.getString(R.string.password_mismatch)
            editFirstNameLayout -> resources.getString(R.string.no_user)
            logEditPasswordLayout -> resources.getString(R.string.invalid_password)
            else -> resources.getString(R.string.field_is_empty)
        }
    } else ""
}