package com.example.cookbook.presentation.view.recipe.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cookbook.domain.Recipes

@Composable
fun RecipeContent (
    padding: PaddingValues,
    recipes: Recipes,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding)
        ) {
            items(
                items = recipes
            ) { recipe ->
                RecipeCard(
                    recipe = recipe
                )
            }
        }
    }