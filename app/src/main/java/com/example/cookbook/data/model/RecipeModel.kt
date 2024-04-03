package com.example.cookbook.data.model

import com.google.firebase.firestore.DocumentId

data class RecipeModel (
    @DocumentId val id: String = "",
    var name: String = "",
    var description: String? = "",
    var ingredientsList: List<Ingredient>? = null,
    val stepsList: List<String?> = emptyList(),
    val type: String? = "",
    val imageUrl: String? = "",
    val cookTime: Long? = null,
    val userUid: String = ""
)