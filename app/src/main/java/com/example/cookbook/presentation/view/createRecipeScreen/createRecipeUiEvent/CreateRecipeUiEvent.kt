package com.example.cookbook.presentation.view.createRecipeScreen.createRecipeUiEvent

import android.net.Uri
import com.example.cookbook.data.model.Ingredient
import com.example.cookbook.data.model.RecipeModel

sealed class CreateRecipeUiEvent {

    data class ChangeSelectedTime(val time: Long) : CreateRecipeUiEvent()
    data class ChangeNameTextValue(val name: String) : CreateRecipeUiEvent()
    data class ChangeDescTextValue(val desc: String) : CreateRecipeUiEvent()
    data class AddToIngredientList(val ingredient: Ingredient) : CreateRecipeUiEvent()
    data class DeleteFromIngredientList(val ingredient: Ingredient) : CreateRecipeUiEvent()
    data class AddToStepList(val step: String) : CreateRecipeUiEvent()
    data class DeleteFromStepList(val step: String) : CreateRecipeUiEvent()
    data object ExpandDropDownMenu : CreateRecipeUiEvent()
    data class ChangeSelectedMenuItem(val item: String) : CreateRecipeUiEvent()
    data class ChangeErrorValue(val errorValue: String) : CreateRecipeUiEvent()
    data class ChangeErrorStatus(val value: Boolean) : CreateRecipeUiEvent()
    data class AddToFirebaseStorage(val uri: Uri?, val recipe: RecipeModel) : CreateRecipeUiEvent()


}