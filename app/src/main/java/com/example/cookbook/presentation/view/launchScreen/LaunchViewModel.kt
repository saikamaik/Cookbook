package com.example.cookbook.presentation.view.launchScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(
    val auth: FirebaseAuth
): ViewModel() {

    var image by mutableStateOf(IntSize.Zero)
    private var currentUser: FirebaseUser? = null

    init {
        currentUser = auth.currentUser
    }

    fun checkForAuthUser(): Boolean {
        return currentUser != null
    }

}