package com.example.cookbook.presentation.view.profileScreen.profileUiState

import com.example.cookbook.data.model.Response
import com.example.cookbook.data.model.UserModel
import com.example.cookbook.domain.RecipeResponse

data class ProfileUiState(
    val user: UserModel? = null,
    val recipeResponse: RecipeResponse = Response.Loading
)