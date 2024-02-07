package com.example.cookbook.presentation.view.homePage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.cookbook.domain.AddRecipeResponse
import com.example.cookbook.model.RecipeModel
import com.example.cookbook.model.Response
import com.example.cookbook.domain.RecipeRepository
import com.example.cookbook.domain.RecipeResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val recipeRep: RecipeRepository) : ViewModel() {

    var recipeResponse by mutableStateOf<RecipeResponse>(Response.Loading)
    var addRecipeResponse by mutableStateOf<AddRecipeResponse>(Response.Success(false))

    fun addRecipe(recipe: RecipeModel) = run {
        viewModelScope.launch {
            recipeRep.addRecipe(recipe)
        }
    }

    init {
        getRecipe()
    }

    private fun getRecipe() = viewModelScope.launch {
        recipeRep.getAllRecipes().collect { response ->
            recipeResponse = response
        }
    }

}