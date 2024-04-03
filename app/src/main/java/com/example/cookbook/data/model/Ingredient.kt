package com.example.cookbook.data.model

typealias IngredientList = List<Ingredient>

data class Ingredient(
    val amount: String? = null,
    val name: String? = null
)
