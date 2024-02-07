package com.example.cookbook.presentation.view.recipe.components

import android.widget.ProgressBar
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cookbook.model.Response
import com.example.cookbook.presentation.view.homePage.HomeViewModel

@Composable
fun AddRecipe(
    viewModel: HomeViewModel = hiltViewModel()
) {
    when(val addBookResponse = viewModel.addRecipeResponse) {
        is Response.Loading -> print("")
        is Response.Success<*> -> Unit
        is Response.Failure -> print(addBookResponse.e)
    }
}