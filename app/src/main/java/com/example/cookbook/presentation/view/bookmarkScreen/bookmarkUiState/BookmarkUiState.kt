package com.example.cookbook.presentation.view.bookmarkScreen.bookmarkUiState

import com.example.cookbook.data.model.Response
import com.example.cookbook.domain.RecipeResponse

data class BookmarkUiState(
    val recipeResponse: RecipeResponse = Response.Loading
)