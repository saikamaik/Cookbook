package com.example.cookbook.presentation.view.searchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.domain.RecipeRepository
import com.example.cookbook.presentation.view.searchScreen.searchUiEvent.SearchUiEvent
import com.example.cookbook.presentation.view.searchScreen.searchUiState.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val recipeRep: RecipeRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<SearchUiState> = MutableStateFlow(SearchUiState())
    var uiState: StateFlow<SearchUiState> = _uiState

    fun postUiEvent(event: SearchUiEvent) {
        when (event) {
            is SearchUiEvent.GetSearchedRecipe -> getSearchedRecipe(event.query)
        }
    }

    init {
        getRecipe()
    }

    private fun getRecipe() = viewModelScope.launch {
        recipeRep.getAllRecipes().collect { response ->
            _uiState.value = _uiState.value.copy(recipeResponse = response)
        }
    }

    private fun getSearchedRecipe(query: String) = viewModelScope.launch {
        recipeRep.getSearchedRecipes(query).collect { response ->
            _uiState.value = _uiState.value.copy(searchedRecipeResponse = response)
        }
    }

}