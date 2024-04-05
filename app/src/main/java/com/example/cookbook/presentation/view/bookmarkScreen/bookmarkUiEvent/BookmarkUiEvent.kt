package com.example.cookbook.presentation.view.bookmarkScreen.bookmarkUiEvent

sealed class BookmarkUiEvent {

    data class DeleteBookmark(val recipeId: String): BookmarkUiEvent()

}