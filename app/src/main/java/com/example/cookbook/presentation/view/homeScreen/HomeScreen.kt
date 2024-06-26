package com.example.cookbook.presentation.view.homeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cookbook.R
import com.example.cookbook.presentation.view.common.TextHeader4
import com.example.cookbook.presentation.view.homeScreen.sections.PopularCategory
import com.example.cookbook.presentation.view.homeScreen.sections.RecentCategory
import com.example.cookbook.presentation.view.homeScreen.uiEvent.HomeUiEvent

@Composable
fun HomeScreen(
    navHostController: NavHostController
) {

    val viewModel: HomeViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .padding(top = 20.dp, bottom = 80.dp, start = 24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TextHeader4(
            header = stringResource(id = R.string.find_best_recipe_with_line_break),
            modifier = Modifier
                .padding(bottom = 16.dp)
        )
        PopularCategory(
            uiState.value.recipeResponse,
            viewModel,
            navHostController,
            onClick = { recipeId ->
                viewModel.postUiEvent(HomeUiEvent.AddRecipeToBookMark(recipeId))
            }
        )
        RecentCategory(
            uiState.value.recipeResponse,
            navHostController = navHostController,
            onClick = { recipeId ->
                viewModel.postUiEvent(HomeUiEvent.AddRecipeToBookMark(recipeId))
            }
        )
    }
}
