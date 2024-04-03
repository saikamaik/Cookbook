package com.example.cookbook.presentation.view.recipeInfoScreen.recipeInfoUiState

import com.example.cookbook.data.model.RecipeModel
import com.example.cookbook.data.model.UserModel

data class RecipeInfoUiState(
    val recipe: RecipeModel = RecipeModel(),
    val user: UserModel = UserModel()
)
