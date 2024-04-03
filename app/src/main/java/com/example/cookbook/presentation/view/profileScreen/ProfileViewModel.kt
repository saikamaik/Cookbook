package com.example.cookbook.presentation.view.profileScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.data.model.RecipeModel
import com.example.cookbook.data.model.Response
import com.example.cookbook.data.model.UserModel
import com.example.cookbook.presentation.view.profileScreen.profileUiState.ProfileUiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val auth: FirebaseAuth,
    private val recipeRef: FirebaseFirestore
) : ViewModel() {

    private val _uiState: MutableStateFlow<ProfileUiState> = MutableStateFlow(ProfileUiState())
    var uiState: StateFlow<ProfileUiState> = _uiState

    init {
        getUser()
        getRecipe()
    }

    private fun getRecipe() = viewModelScope.launch {
        recipeRef.collection("recipe")
            .whereEqualTo("userUid", auth.currentUser?.uid)
            .get()
            .addOnSuccessListener { documents ->
                val recipeResponse = if (documents != null) {
                    val recipe = documents.toObjects(RecipeModel::class.java)
                    Response.Success(recipe)
                } else {
                    Response.Failure(e = null)
                }
                _uiState.value = _uiState.value.copy(recipeResponse = recipeResponse)
            }
    }

    private fun getUser() = viewModelScope.launch {
        val currentUser = auth.currentUser

        val userUid = currentUser?.uid.toString()
        val userDisplayName = currentUser?.displayName
        val userPhotoUrl = currentUser?.photoUrl

        val userModel: UserModel? = userDisplayName?.let {
            UserModel(
                userUid,
                it,
                userPhotoUrl.toString()
            )
        }
        _uiState.value = _uiState.value.copy(user = userModel)
    }

}