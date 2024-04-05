package com.example.cookbook.presentation.view.homeScreen.uiState

import com.example.cookbook.data.model.Response
import com.example.cookbook.domain.RecipeResponse

data class HomeUiState(
    val selectedTabOption: String = "Salad",
    val recipeResponse: RecipeResponse = Response.Loading,
    val bookmarkResponse: Response<Boolean> = Response.Loading
)