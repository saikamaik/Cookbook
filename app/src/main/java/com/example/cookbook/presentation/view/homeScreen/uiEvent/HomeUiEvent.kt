package com.example.cookbook.presentation.view.homeScreen.uiEvent

sealed class HomeUiEvent {
    data class ChangeSelectedTabOption(val item: String): HomeUiEvent()
    data class AddRecipeToBookMark(val recipeId: String): HomeUiEvent()

}