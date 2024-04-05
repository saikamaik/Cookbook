package com.example.cookbook.presentation.view.bookmarkScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.domain.RecipeRepository
import com.example.cookbook.presentation.view.bookmarkScreen.bookmarkUiEvent.BookmarkUiEvent
import com.example.cookbook.presentation.view.bookmarkScreen.bookmarkUiState.BookmarkUiState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val recipeRep: RecipeRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<BookmarkUiState> = MutableStateFlow(BookmarkUiState())
    var uiState: StateFlow<BookmarkUiState> = _uiState

    fun postUiEvent(event: BookmarkUiEvent) {
        when (event) {
            is BookmarkUiEvent.DeleteBookmark -> deleteBookmark(event.recipeId)
        }
    }

    init {
        getAllBookmarkedRecipes()
    }

    private fun getAllBookmarkedRecipes() {
        viewModelScope.launch {
            auth.currentUser?.let {
                recipeRep.getAllBookmarkedRecipes(it.uid).collect { response ->
                    _uiState.value = _uiState.value.copy(recipeResponse = response)
                }
            }
        }
    }

    private fun deleteBookmark(recipeId: String) = viewModelScope.launch {
        recipeRep.deleteBookmarkRecipe(recipeId)
    }

}