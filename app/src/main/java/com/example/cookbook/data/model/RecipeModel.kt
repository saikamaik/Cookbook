package com.example.cookbook.data.model

import com.google.firebase.firestore.DocumentId

data class RecipeModel (
    @DocumentId val id: String = "",
    var name: String = "",
    var description: String? = "",
    var ingredientsList: List<Ingredient> = listOf(),
    val stepsList: List<String> = listOf(),
    val type: String? = "",
    val imageUrl: String? = "",
    val cookTime: Long? = null,
    val userUid: String = ""
)