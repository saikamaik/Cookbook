package com.example.cookbook.presentation.view.homeScreen.sections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cookbook.R
import com.example.cookbook.domain.RecipeResponse
import com.example.cookbook.presentation.view.common.TextHeader
import com.example.cookbook.presentation.view.common.recipe.Recipes
import com.example.cookbook.presentation.view.common.tabs.TabButtonBar
import com.example.cookbook.presentation.view.homeScreen.HomeViewModel
import com.example.cookbook.presentation.view.homeScreen.sections.components.RecipeContent

@Composable
fun PopularCategory(
    recipeResponse: RecipeResponse,
    viewModel: HomeViewModel,
    navHostController: NavHostController,
    onClick: (String) -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    val labels = listOf(
        "Salad", "Breakfast", "Lunch", "Appetizer", "Dessert"
    )

    Column(
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        TextHeader(
            header = stringResource(id = R.string.popular_category),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        TabButtonBar(
            labels = labels,
            selectedOption = uiState.selectedTabOption,
            viewModel = viewModel
        )
    }

    Recipes(
        recipeResponse,
        recipeContent = { recipes ->
            RecipeContent(
                recipes = recipes,
                uiState.selectedTabOption,
                navController = navHostController
            ) {
                onClick(it)
            }
        }
    )

}
