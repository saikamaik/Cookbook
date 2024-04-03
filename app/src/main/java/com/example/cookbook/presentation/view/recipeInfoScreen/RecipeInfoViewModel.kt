package com.example.cookbook.presentation.view.recipeInfoScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.data.model.RecipeModel
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldPath
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeInfoViewModel @Inject constructor(
    private val recipeRef: CollectionReference,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val argument: String = savedStateHandle["id"]!!
    var recipe by mutableStateOf<RecipeModel?>(null)

    //uiState
    init {
        getOneRecipe(argument)
    }

    private fun getOneRecipe(id: String) = viewModelScope.launch {
        recipeRef.whereEqualTo(FieldPath.documentId(), id)
            .get()
            .addOnSuccessListener { documents ->
                val recipeList = documents?.toObjects(RecipeModel::class.java)
                if (recipeList != null) {
                    for (item in recipeList) {
                        if (item.id == id) {
                            recipe = item
                        }
                    }
                }
            }
    }


}