package com.example.cookbook.presentation.view.searchScreen.searchUiState

import com.example.cookbook.data.model.RecipeModel
import com.example.cookbook.data.model.Response
import com.example.cookbook.domain.RecipeResponse

data class SearchUiState (
    val recipe: RecipeModel = RecipeModel(),
    val recipeResponse: RecipeResponse = Response.Loading,
    val searchedRecipeResponse: RecipeResponse = Response.Loading
)