package com.example.cookbook.presentation.view.searchScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cookbook.data.model.Response
import com.example.cookbook.domain.Recipes
import com.example.cookbook.presentation.view.common.search.NoSearchResults
import com.example.cookbook.presentation.view.common.search.rememberSearchState
import com.example.cookbook.presentation.view.common.search.SearchBar
import com.example.cookbook.presentation.view.common.search.SearchDisplay
import com.example.cookbook.presentation.view.common.search.SearchState
import kotlinx.coroutines.delay

@Composable
fun SearchScreen(
    navHostController: NavHostController
) {

    val viewModel: SearchViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState()
    val state: SearchState = rememberSearchState()

    Column(
        modifier = Modifier.fillMaxSize().padding(start = 20.dp, end = 20.dp, top = 12.dp, bottom = 60.dp)
    ) {

        SearchBar(
            query = state.query,
            onQueryChange = { state.query = it },
            onSearchFocusChange = { state.focused = it },
            onClearQuery = { state.query = TextFieldValue("") },
            onBack = { state.query = TextFieldValue("") },
            searching = state.searching,
            focused = state.focused
        )

        LaunchedEffect(state.query.text) {
            viewModel.getSearchedRecipe(state.query.text)
            state.searching = true
            delay(100)
            when (viewModel.uiState.value.searchedRecipeResponse) {
                is Response.Loading -> print("loading")
                is Response.Success -> state.searchResults = (uiState.value.searchedRecipeResponse as Response.Success<Recipes>).data
                is Response.Failure -> print((uiState.value.searchedRecipeResponse as Response.Failure).e)
            }
            state.searching = false
        }

        when (state.searchDisplay) {

            SearchDisplay.InitialResults -> {
                SearchInitialResults(
                    recipeResponse = uiState.value.recipeResponse,
                    navHostController = navHostController
                )
            }

            SearchDisplay.NoResults -> {
                NoSearchResults()
            }

            SearchDisplay.Suggestions -> {

            }

            SearchDisplay.Results -> {
                SearchInitialContent(
                    recipes = state.searchResults,
                    navController = navHostController)
            }
        }

    }
}
