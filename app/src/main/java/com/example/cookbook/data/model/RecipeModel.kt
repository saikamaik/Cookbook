package com.example.cookbook.data.model

import com.google.firebase.firestore.DocumentId

data class RecipeModel (
    @DocumentId val id: String = "",
    var name: String = "",
    var description: String? = "",
    var ingredientsList: List<Ingredient?> = emptyList(),
    val stepsList: List<String?> = emptyList(),
    val imageUrl: String? = "",
    val cookTime: Long? = null
)