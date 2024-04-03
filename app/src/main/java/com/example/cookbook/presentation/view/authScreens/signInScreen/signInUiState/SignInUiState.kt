package com.example.cookbook.presentation.view.authScreens.signInScreen.signInUiState

import com.google.firebase.auth.FirebaseUser

data class SignInUiState (

    val emailTextFieldValue: String = "",
    val passwordTextFieldValue: String = "",
    val userUid: String = "",
    val isError: Boolean = false,
    val errorText: String = "",
    val passwordVisibility: Boolean = false,
    val signInResponse: String = "",
    val user: FirebaseUser? = null

)