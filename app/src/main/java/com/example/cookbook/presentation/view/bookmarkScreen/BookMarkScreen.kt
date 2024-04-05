package com.example.cookbook.presentation.view.bookmarkScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cookbook.R
import com.example.cookbook.presentation.view.bookmarkScreen.bookmarkUiEvent.BookmarkUiEvent
import com.example.cookbook.presentation.view.bookmarkScreen.components.BookmarkContent
import com.example.cookbook.presentation.view.common.TextHeader
import com.example.cookbook.presentation.view.common.recipe.Recipes

@Composable
fun BookMarkScreen(
    navHostController: NavHostController
) {

    val viewModel: BookmarkViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.padding(
            start = 14.dp,
            end = 20.dp,
            top = 20.dp,
            bottom = 80.dp
        )
    ) {

        TextHeader(
            header = stringResource(id = R.string.saved_recipes),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(
                    top = 16.dp,
                    bottom = 16.dp,
                )
        )

        Recipes(
            response = uiState.value.recipeResponse
        ) {
            BookmarkContent(
                recipes = it,
                navController = navHostController,
                onClick = { recipeId ->
                    viewModel.postUiEvent(BookmarkUiEvent.DeleteBookmark(recipeId))
                }
            )
        }
    }
}