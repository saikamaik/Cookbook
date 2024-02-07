package com.example.cookbook.presentation.view.recipe.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cookbook.model.Response
import com.example.cookbook.domain.Recipes
import com.example.cookbook.presentation.view.homePage.HomeViewModel

@Composable
fun Recipes(
    viewModel: HomeViewModel = hiltViewModel(),
    recipeContent: @Composable (recipe: Recipes) -> Unit
) {
    when(val recipeResponse = viewModel.recipeResponse) {
        is Response.Loading -> print("loading")
        is Response.Success -> recipeContent(recipeResponse.data)
        is Response.Failure -> print(recipeResponse.e)
    }
}