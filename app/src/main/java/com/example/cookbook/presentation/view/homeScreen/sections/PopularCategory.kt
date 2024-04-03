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
import com.example.cookbook.presentation.view.homeScreen.HomeViewModel
import com.example.cookbook.presentation.view.common.TextHeader
import com.example.cookbook.presentation.view.common.tabs.TabButtonBar
import com.example.cookbook.presentation.view.homeScreen.sections.components.RecipeContent
import com.example.cookbook.presentation.view.common.recipe.Recipes

@Composable
fun PopularCategory(
    viewModel: HomeViewModel,
    navHostController: NavHostController
) {

    val uiState by viewModel.uiState.collectAsState()

    val labels = listOf(
        "Salad", "Breakfast", "Appetizer", "Noodle"
    )

    Column (
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
        viewModel.recipeResponse,
        recipeContent = {
            recipes ->
        RecipeContent(recipes = recipes, uiState.selectedTabOption, navController = navHostController)
        }
    )

}
