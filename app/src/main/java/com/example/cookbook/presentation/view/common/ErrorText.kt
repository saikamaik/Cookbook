package com.example.cookbook.presentation.view.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cookbook.data.model.RecipeModel
import com.example.cookbook.presentation.view.createRecipeScreen.CreateRecipeViewModel
import com.example.cookbook.presentation.view.createRecipeScreen.createRecipeUiEvent.CreateRecipeUiEvent
import com.example.cookbook.ui.theme.PrimaryRed50

fun checkRecipeForError(
    recipeForCheck: RecipeModel,
    viewModel: CreateRecipeViewModel
) {

    val listOfItems = listOf(
        recipeForCheck.name,
        recipeForCheck.description.toString(),
        recipeForCheck.ingredientsList.toString(),
        recipeForCheck.stepsList.toString(),
        recipeForCheck.imageUrl.toString()
    )

    for (item in listOfItems) {
        if (item.isBlank() || item.isEmpty()) {
            viewModel.postUiEvent(CreateRecipeUiEvent.ChangeErrorStatus(true))
        }
    }

}

@Composable
fun ErrorText(
    errorText: String,
    textForCheck: List<String?>,
    modifier: Modifier
) {

    var isError = false

    for (item in textForCheck) {
        if (item != null) {
            if (errorText.isNotEmpty() && item.isBlank()) {
                isError = true
            }
        }
    }

    if (isError) {
        TextMedium(
            text = errorText,
            color = PrimaryRed50,
            modifier = modifier
        )
    }
}