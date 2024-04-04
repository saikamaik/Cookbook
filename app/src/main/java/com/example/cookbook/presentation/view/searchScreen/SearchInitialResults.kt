package com.example.cookbook.presentation.view.searchScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cookbook.domain.RecipeResponse
import com.example.cookbook.domain.Recipes
import com.example.cookbook.presentation.view.common.recipe.RecipeCard
import com.example.cookbook.presentation.view.common.recipe.Recipes

@Composable
fun SearchInitialResults(
    recipeResponse: RecipeResponse,
    navHostController: NavHostController
) {

    Recipes(
        recipeResponse,
        recipeContent = { recipes ->
            SearchInitialContent(recipes = recipes, navController = navHostController)
        }
    )

}

@Composable
fun SearchInitialContent(
    recipes: Recipes,
    navController: NavHostController
) {

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = recipes) { recipe ->
            RecipeCard(
                recipeModel = recipe,
                navController
            ) {

            }
        }
    }
}