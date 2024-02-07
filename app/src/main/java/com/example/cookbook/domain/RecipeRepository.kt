package com.example.cookbook.domain

import com.example.cookbook.model.RecipeModel
import com.example.cookbook.model.Response
import kotlinx.coroutines.flow.Flow

typealias Recipes = List<RecipeModel>
typealias RecipeResponse = Response<Recipes>
typealias AddRecipeResponse = Response<Boolean>

interface RecipeRepository {

    suspend fun addRecipe(recipe: RecipeModel): AddRecipeResponse
    fun deleteRecipe(recipe: RecipeModel)
    fun getAllRecipes(): Flow<RecipeResponse>

}