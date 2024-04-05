package com.example.cookbook.presentation.view.authScreens.signInScreen

import android.annotation.SuppressLint
import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.data.model.Response
import com.example.cookbook.domain.AuthRepository
import com.example.cookbook.domain.SignInResponse
import com.example.cookbook.presentation.view.authScreens.signInScreen.signInUiEvent.SignInUiEvent
import com.example.cookbook.presentation.view.authScreens.signInScreen.signInUiState.SignInUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    @ApplicationContext val context: Context
) : ViewModel() {

    private val _uiState: MutableStateFlow<SignInUiState> =
        MutableStateFlow(SignInUiState())
    var uiState: StateFlow<SignInUiState> = _uiState

    fun postUiEvent(event: SignInUiEvent) {
        when (event) {
            is SignInUiEvent.ChangeEmailTextValue -> changeEmailTextValue(event.email)
            is SignInUiEvent.ChangePasswordTextValue -> changePasswordTextValue(event.password)
            is SignInUiEvent.ChangeErrorStatus -> changeErrorStatus(event.status)
            is SignInUiEvent.ChangePasswordVisibility -> changePasswordVisibility()
            is SignInUiEvent.ChangeErrorValue -> changeErrorTextValue(event.value)
            is SignInUiEvent.ChangeSignInResponse -> changeSignInResponse(event.value)
            is SignInUiEvent.SignInWithEmail -> signInWithEmail(
                event.email,
                event.password
            ) { event.onSuccess() }
        }
    }

    private fun changeSignInResponse(value: String) {
        _uiState.value = _uiState.value.copy(signInResponse = value)
    }

    private fun changePasswordVisibility() {
        _uiState.value =
            _uiState.value.copy(passwordVisibility = !_uiState.value.passwordVisibility)
    }

    private fun changeErrorTextValue(value: String) {
        _uiState.value = _uiState.value.copy(errorText = value)
    }

    private fun changeEmailTextValue(email: String) {
        _uiState.value = _uiState.value.copy(emailTextFieldValue = email)
    }

    private fun changePasswordTextValue(password: String) {
        _uiState.value = _uiState.value.copy(passwordTextFieldValue = password)
    }

    private fun changeErrorStatus(status: Boolean) {
        _uiState.value = _uiState.value.copy(isError = status)
    }

    fun emailAndPasswordValidation(email: String, password: String): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            changeErrorStatus(true)
            return false
        }
        return if (email.isEmpty() || password.isEmpty()) {
            changeErrorStatus(true)
            false
        } else {
            true
        }
    }

    private fun signInWithEmail(email: String, password: String, onSuccess: () -> Unit) =
        viewModelScope.launch {
            try {
                authRepository.signInWithEmailAndPassword(email, password).collect {
                    signInResponseContent(
                        context,
                        it,
                        onSuccess
                    )
                }
            } catch (_: Exception) {

            }
        }

    private fun signInResponseContent(
        context: Context,
        response: SignInResponse,
        signInContent: () -> Unit
    ) {
        when (response) {
            is Response.Loading -> Toast.makeText(
                context,
                "Loading",
                Toast.LENGTH_SHORT
            ).show()
            is Response.Success -> signInContent()
            is Response.Failure -> Toast.makeText(
                context,
                response.e,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}