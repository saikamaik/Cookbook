package com.example.cookbook.presentation.view.authScreens.signUpScreen.signUpUiState

import android.net.Uri

data class SignUpUiState(

    val emailTextFieldValue: String = "",
    val passwordTextFieldValue: String = "",
    val userNameTextFieldValue: String = "",
    val userUid: String = "",
    val isError: Boolean = false,
    val errorText: String = "",
    val passwordVisibility: Boolean = false,
    val signUpResponse: String = "",
    var selectedImageUri: Uri? = null

)