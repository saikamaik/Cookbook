package com.example.cookbook.presentation.view.common.recipe

import androidx.compose.runtime.Composable
import com.example.cookbook.data.model.Response
import com.example.cookbook.domain.RecipeResponse
import com.example.cookbook.domain.Recipes
import com.example.cookbook.presentation.view.common.LoadingField

@Composable
fun Recipes(
    response: RecipeResponse,
    recipeContent: @Composable (recipe: Recipes) -> Unit
) {
    when (response) {
        is Response.Loading -> LoadingField()
        is Response.Success -> recipeContent(response.data)
        is Response.Failure -> print(response.e)
    }
}