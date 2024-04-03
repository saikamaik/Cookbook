package com.example.cookbook.domain

import com.example.cookbook.data.model.RecipeModel
import com.example.cookbook.data.model.Response
import kotlinx.coroutines.flow.Flow

typealias Recipes = List<RecipeModel>
typealias RecipeResponse = Response<Recipes>
typealias AddRecipeResponse = Response<Boolean>
typealias UpdateRecipeResponse = Response<Boolean>

interface RecipeRepository {

    suspend fun addRecipe(recipe: RecipeModel): AddRecipeResponse
    fun deleteRecipe(recipe: RecipeModel)
    fun getAllRecipes(): Flow<RecipeResponse>
    fun getOneRecipe(id: String): Flow<RecipeModel>
    suspend fun updateRecipe(recipe: RecipeModel): UpdateRecipeResponse

}