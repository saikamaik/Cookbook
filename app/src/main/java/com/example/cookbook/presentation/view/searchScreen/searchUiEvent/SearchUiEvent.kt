package com.example.cookbook.presentation.view.searchScreen.searchUiEvent

sealed class SearchUiEvent {

    data class GetSearchedRecipe(val query: String): SearchUiEvent()

}