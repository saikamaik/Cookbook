package com.example.cookbook.useCase

import com.example.cookbook.domain.RecipeRepository

class GetRecipe (
    private val repo: RecipeRepository
) {
    operator fun invoke() = repo.getAllRecipes()
}