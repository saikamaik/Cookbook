package com.example.cookbook.presentation.view.createRecipeScreen.createRecipeUiState

import com.example.cookbook.data.model.IngredientList
import com.maxkeppeker.sheets.core.models.base.UseCaseState

data class CreateRecipeUiState(
    val recipeName: String = "",
    val recipeImageUri: String = "",

    val recipeNameTextFieldValue: String = "",
    val recipeDescTextFieldValue: String = "",

    val listOfIngredients: IngredientList = listOf(),
    val ingredientNameTextFieldValue: String = "",
    val ingredientAmountTextFieldValue: String = "",

    val listOfSteps: List<String> = listOf(),

    val selectedMenuItem: String = "Salad",
    val isDropDownMenuExpanded: Boolean = false,

    val selectedTimeInSeconds: Long = 60,
    val timePickerState: UseCaseState = UseCaseState(visible = false)
)