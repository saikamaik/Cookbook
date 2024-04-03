package com.example.cookbook.presentation.view.recipeInfoScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.domain.AuthRepository
import com.example.cookbook.domain.RecipeRepository
import com.example.cookbook.presentation.view.recipeInfoScreen.recipeInfoUiState.RecipeInfoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeInfoViewModel @Inject constructor(
    private val recipeRep: RecipeRepository,
    private val authRep: AuthRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val argument: String = savedStateHandle["id"]!!

    private val _uiState: MutableStateFlow<RecipeInfoUiState> =
        MutableStateFlow(RecipeInfoUiState())
    var uiState: StateFlow<RecipeInfoUiState> = _uiState

    //uiState
    init {
        getOneRecipe(argument)
    }

    private fun getOneRecipe(id: String) = viewModelScope.launch {
        recipeRep.getOneRecipe(id).collect() {
            _uiState.value = _uiState.value.copy(recipe = it)

            if (_uiState.value.recipe.userUid.isNotEmpty()) {
                authRep.getOneUser(_uiState.value.recipe.userUid).collect() {
                    _uiState.value = _uiState.value.copy(user = it)
                }
            }
        }
    }

}