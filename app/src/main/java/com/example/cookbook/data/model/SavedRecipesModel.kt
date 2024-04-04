package com.example.cookbook.data.model

import com.google.firebase.firestore.DocumentId

data class SavedRecipesModel (
    @DocumentId val id: String = "",
    val userId: String? = null,
    val recipeId: String? = null
)