package com.example.cookbook.presentation.view.profileScreen.components

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
import com.example.cookbook.domain.Recipes
import com.example.cookbook.presentation.view.common.recipe.RecipeCard

@Composable
fun UserRecipeContent(
    recipes: Recipes,
    navHostController: NavHostController,
    onClick: (String) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 20.dp, start = 20.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(items = recipes) { recipe ->
            RecipeCard(
                recipeModel = recipe,
                navHostController
            ) {
                onClick(it)
            }
        }
    }

}