package com.example.cookbook.useCase

import com.example.cookbook.domain.RecipeRepository
import com.example.cookbook.data.model.RecipeModel

class AddRecipe(
    private val repo: RecipeRepository
) {
    suspend operator fun invoke(
        recipe: RecipeModel
    ) = repo.addRecipe(recipe)
}