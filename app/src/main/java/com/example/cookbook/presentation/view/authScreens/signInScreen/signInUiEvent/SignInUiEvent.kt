package com.example.cookbook.presentation.view.authScreens.signInScreen.signInUiEvent

sealed class SignInUiEvent {

    data class ChangeEmailTextValue(val email: String) : SignInUiEvent()
    data class ChangePasswordTextValue(val password: String) : SignInUiEvent()
    data class ChangeErrorStatus(val status: Boolean) : SignInUiEvent()
    data class ChangeErrorValue(val value: String) : SignInUiEvent()
    data class ChangeSignInResponse(val value: String) : SignInUiEvent()
    data object ChangePasswordVisibility : SignInUiEvent()
    data class ValidateEmailAndPassword(val email: String, val password: String): SignInUiEvent()

}