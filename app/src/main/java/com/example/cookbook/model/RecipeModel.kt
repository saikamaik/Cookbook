package com.example.cookbook.model

import com.example.cookbook.Ingredients
import com.google.firebase.firestore.DocumentId

data class RecipeModel (
    @DocumentId val id: String = "",
    var name: String = "",
    var shortDesc: String? = null,
    var description: String? = "",
    var ingredientsList: ArrayList<String>? = null

    //TODO поле для медиа файлов? или для заметок
)