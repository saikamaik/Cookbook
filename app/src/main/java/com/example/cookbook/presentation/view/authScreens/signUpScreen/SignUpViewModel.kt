package com.example.cookbook.presentation.view.authScreens.signUpScreen

import android.content.Context
import android.net.Uri
import android.util.Patterns
import android.widget.Toast
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.ViewModel
import com.example.cookbook.R
import com.example.cookbook.data.model.UserModel
import com.example.cookbook.presentation.view.authScreens.signUpScreen.signUpUiEvent.SignUpUiEvent
import com.example.cookbook.presentation.view.authScreens.signUpScreen.signUpUiState.SignUpUiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val userRef: FirebaseFirestore,
    private val imageStorageReference: StorageReference,
    @ApplicationContext val appContext: Context
) : ViewModel() {

    private val _uiState: MutableStateFlow<SignUpUiState> =
        MutableStateFlow(SignUpUiState())
    var uiState: StateFlow<SignUpUiState> = _uiState

    fun postUiEvent(event: SignUpUiEvent) {
        when (event) {
            is SignUpUiEvent.ChangeEmailTextValue -> changeEmailTextValue(event.email)
            is SignUpUiEvent.ChangePasswordTextValue -> changePasswordTextValue(event.password)
            is SignUpUiEvent.SignUpWithEmail -> signUpWithEmail(
                event.email,
                event.password,
                event.username
            )
            is SignUpUiEvent.ChangeErrorStatus -> changeErrorStatus(event.status)
            is SignUpUiEvent.ChangeUserNameTextValue -> changeUserNameTextValue(event.username)
            is SignUpUiEvent.ChangePasswordVisibility -> changePasswordVisibility()
            is SignUpUiEvent.ChangeErrorValue -> changeErrorTextValue(event.value)
            is SignUpUiEvent.ChangeSignUpResponse -> changeSignUpResponse(event.value)
            is SignUpUiEvent.ChangeSelectedImageUri -> changeSelectedImageUri(event.uri)
        }
    }

    private fun changeSelectedImageUri(uri: Uri) {
        _uiState.value = _uiState.value.copy(selectedImageUri = uri)
    }

    private fun changeSignUpResponse(value: String) {
        _uiState.value = _uiState.value.copy(signUpResponse = value)
    }

    private fun changePasswordVisibility() {
        _uiState.value =
            _uiState.value.copy(passwordVisibility = !_uiState.value.passwordVisibility)
    }

    private fun changeErrorTextValue(value: String) {
        _uiState.value = _uiState.value.copy(errorText = value)
    }

    private fun changeUserNameTextValue(username: String) {
        _uiState.value = _uiState.value.copy(userNameTextFieldValue = username)
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

    fun validateCredentials(email: String?, password: String?, username: String?): Boolean {
        if (email?.let { Patterns.EMAIL_ADDRESS.matcher(it).matches() } == true) {
            Toast.makeText(
                appContext,
                getString(appContext, R.string.enter_valid_cred),
                Toast.LENGTH_SHORT
            ).show()
            changeSignUpResponse(getString(appContext, R.string.enter_valid_cred))
            return false
        } else if (password != null) {
            if (password.length < 6) {
                Toast.makeText(
                    appContext,
                    getString(appContext, R.string.password_long),
                    Toast.LENGTH_SHORT
                ).show()
                changeSignUpResponse(getString(appContext, R.string.enter_valid_cred))
                return false
            }
        }
        if (username == null) {
            changeSignUpResponse(getString(appContext, R.string.enter_valid_cred))
            return false
        }
        return true
    }

    private fun signUpWithEmail(email: String, password: String, username: String) {
        try {
            auth.createUserWithEmailAndPassword(email, password)
            val profileUpdates = userProfileChangeRequest {
                displayName = username
            }
            auth.currentUser!!.updateProfile(profileUpdates)
        } catch (e: FirebaseAuthUserCollisionException) {
            changeSignUpResponse(getString(appContext, R.string.email_already_taken))
        } catch (e: Exception) {
            changeSignUpResponse(e.toString())
        }
    }

    fun addPhotoToFirebaseStorage(uri: Uri) {
        val riversRef = imageStorageReference.child("user_profile_images/${uri.lastPathSegment}")
        val uploadTask = riversRef.putFile(uri)

        uploadTask.addOnFailureListener {
        }.addOnSuccessListener { taskSnapshot ->
            taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                _uiState.value = _uiState.value.copy(selectedImageUri = it)
                val profileUpdates = userProfileChangeRequest {
                    photoUri = it
                }
                auth.currentUser?.updateProfile(profileUpdates)

                userRef.collection("user").add(
                    UserModel(
                        auth.currentUser?.uid.toString(),
                        auth.currentUser?.displayName.toString(),
                        auth.currentUser?.photoUrl.toString()
                    )
                )
            }
        }
    }
}