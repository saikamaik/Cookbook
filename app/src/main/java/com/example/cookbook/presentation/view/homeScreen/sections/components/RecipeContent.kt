package com.example.cookbook.presentation.view.homeScreen.sections.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cookbook.data.model.RecipeModel
import com.example.cookbook.domain.Recipes
import com.example.cookbook.presentation.view.common.recipe.RecipeCard

@Composable
fun RecipeContent (
    recipes: Recipes,
    type: String?,
    navController: NavHostController
) {

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val typedRecipes = mutableListOf<RecipeModel>()
        if (type != null) {
            for (recipe in recipes) {
                if (recipe.type == type) {
                    typedRecipes += recipe
                }
            }
            items(items = typedRecipes) { recipe ->
                RecipeCard(
                    recipeModel = recipe,
                    navController = navController
                ) {

                }
            }
        }
        else {
        items(items = recipes) { recipe ->
            RecipeCard(
                recipeModel = recipe,
                navController
            ) {

            }
        }
    }
        }

    }