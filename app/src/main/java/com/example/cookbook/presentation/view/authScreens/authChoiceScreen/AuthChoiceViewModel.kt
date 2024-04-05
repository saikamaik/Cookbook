package com.example.cookbook.presentation.view.authScreens.authChoiceScreen

import android.content.Context
import android.widget.Toast
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.ViewModel
import com.example.cookbook.R
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class AuthChoiceViewModel @Inject constructor(
    private val authRef: FirebaseAuth,
    @ApplicationContext val appContext: Context
) : ViewModel() {

    fun signInAnon() {
        authRef.signInAnonymously()
            .addOnCompleteListener() { task ->
                if (!task.isSuccessful) {
                    Toast.makeText(
                        appContext,
                        getString(appContext, R.string.auth_failed),
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }
}