package com.example.cookbook.presentation.view.homePage

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.cookbook.model.RecipeModel
import com.example.cookbook.presentation.view.recipe.components.AddRecipe
import com.example.cookbook.presentation.view.recipe.components.AddRecipeActionButton
import com.example.cookbook.presentation.view.recipe.components.AddRecipeDialog
import com.example.cookbook.presentation.view.recipe.components.RecipeContent
import com.example.cookbook.presentation.view.recipe.components.Recipes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeUI(
    viewModel: HomeViewModel = hiltViewModel()
) {

    var openDialog by remember { mutableStateOf(false) }

    Scaffold(
        content = { paddingValues ->
            Recipes(
                recipeContent = { recipes ->
                    RecipeContent(
                        padding = paddingValues,
                        recipes = recipes
                    )
                }
            )
            if (openDialog) {
                AddRecipeDialog(
                    closeDialog = {
                        openDialog = false
                    },
                    addRecipe = { name, shortDesc ->
                        viewModel.addRecipe(
                            recipe = RecipeModel(
                                name, shortDesc
                            )
                        )
                    }
                )
            }

        },
        floatingActionButton = {
            AddRecipeActionButton(
                openDialog = {
                    openDialog = true
                }
            )
        }
    )
    AddRecipe()
}
