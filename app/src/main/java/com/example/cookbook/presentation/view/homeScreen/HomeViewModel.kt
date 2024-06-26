package com.example.cookbook.presentation.view.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.domain.RecipeRepository
import com.example.cookbook.presentation.view.homeScreen.uiEvent.HomeUiEvent
import com.example.cookbook.presentation.view.homeScreen.uiState.HomeUiState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recipeRep: RecipeRepository,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    var uiState: StateFlow<HomeUiState> = _uiState

    fun postUiEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.ChangeSelectedTabOption -> onSelectionChange(event.item)
            is HomeUiEvent.AddRecipeToBookMark -> addRecipeToBookmark(event.recipeId)
        }
    }

    init {
        getRecipe()
    }

    private fun onSelectionChange(item: String) {
        _uiState.value = _uiState.value.copy(selectedTabOption = item)
    }

    private fun getRecipe() = viewModelScope.launch {
        recipeRep.getAllRecipes().collect { response ->
            _uiState.value = _uiState.value.copy(recipeResponse = response)
        }
    }

    private fun addRecipeToBookmark(recipeId: String) = viewModelScope.launch {
        auth.currentUser?.let {
            recipeRep.addRecipeToBookmark(recipeId, it.uid).collect() { response ->
                _uiState.value = _uiState.value.copy(bookmarkResponse = response)
            }
        }
    }

}