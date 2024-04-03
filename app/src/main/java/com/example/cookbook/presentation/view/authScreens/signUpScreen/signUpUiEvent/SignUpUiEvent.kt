package com.example.cookbook.presentation.view.authScreens.signUpScreen.signUpUiEvent

import android.net.Uri

sealed class SignUpUiEvent {

    data class ChangeEmailTextValue(val email: String) : SignUpUiEvent()
    data class ChangePasswordTextValue(val password: String) : SignUpUiEvent()
    data class ChangeUserNameTextValue(val username: String) : SignUpUiEvent()
    data class SignUpWithEmail(val email: String, val password: String, val username: String) :
        SignUpUiEvent()

    data class ChangeErrorStatus(val status: Boolean) : SignUpUiEvent()
    data class ChangeErrorValue(val value: String) : SignUpUiEvent()
    data class ChangeSignUpResponse(val value: String) : SignUpUiEvent()
    data object ChangePasswordVisibility : SignUpUiEvent()
    data class ChangeSelectedImageUri(val uri: Uri) : SignUpUiEvent()

}