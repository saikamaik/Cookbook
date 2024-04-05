package com.example.cookbook.presentation.view.createRecipeScreen

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.data.model.Ingredient
import com.example.cookbook.data.model.RecipeModel
import com.example.cookbook.domain.RecipeRepository
import com.example.cookbook.presentation.view.createRecipeScreen.createRecipeUiEvent.CreateRecipeUiEvent
import com.example.cookbook.presentation.view.createRecipeScreen.createRecipeUiState.CreateRecipeUiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateRecipeViewModel @Inject constructor(
    private val imageStorageReference: StorageReference,
    private val recipeRep: RecipeRepository,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _uiState: MutableStateFlow<CreateRecipeUiState> =
        MutableStateFlow(CreateRecipeUiState())
    var uiState: StateFlow<CreateRecipeUiState> = _uiState

    fun postUiEvent(event: CreateRecipeUiEvent) {
        when (event) {
            is CreateRecipeUiEvent.ChangeSelectedTime -> changeSelectedTime(event.time)
            is CreateRecipeUiEvent.ChangeNameTextValue -> changeNameTextValue(event.name)
            is CreateRecipeUiEvent.ChangeDescTextValue -> changeDescTextValue(event.desc)
            is CreateRecipeUiEvent.AddToIngredientList -> addToIngredientList(event.ingredient)
            is CreateRecipeUiEvent.DeleteFromIngredientList -> deleteFromIngredientList(event.ingredient)
            is CreateRecipeUiEvent.AddToStepList -> addToStepList(event.step)
            is CreateRecipeUiEvent.DeleteFromStepList -> deleteFromStepList(event.step)
            is CreateRecipeUiEvent.ExpandDropDownMenu -> expandDropDownMenu()
            is CreateRecipeUiEvent.ChangeSelectedMenuItem -> changeSelectedMenuItem(event.item)
            is CreateRecipeUiEvent.ChangeErrorValue -> changeErrorValue(event.errorValue)
            is CreateRecipeUiEvent.ChangeErrorStatus -> changeErrorStatus(event.value)
            is CreateRecipeUiEvent.AddToFirebaseStorage -> addPhotoToFirebaseStorage(event.uri, event.recipe)
        }
    }

    private fun changeErrorStatus(value: Boolean) {
        _uiState.value = _uiState.value.copy(isError = value)
    }

    private fun changeErrorValue(errorValue: String) {
        _uiState.value = _uiState.value.copy(errorText = errorValue)
    }

    private fun deleteFromIngredientList(ingredient: Ingredient) {
        val newList = _uiState.value.listOfIngredients.toMutableList()
        newList.remove(ingredient)
        _uiState.value = _uiState.value.copy(
            listOfIngredients = newList
        )
    }

    private fun addToIngredientList(ingredient: Ingredient) {
        val newList = _uiState.value.listOfIngredients.toMutableList()
        newList.add(ingredient)
        _uiState.value = _uiState.value.copy(
            listOfIngredients = newList
        )
    }

    private fun deleteFromStepList(step: String) {
        val newList = _uiState.value.listOfSteps.toMutableList()
        newList.remove(step)
        _uiState.value = _uiState.value.copy(
            listOfSteps = newList
        )
    }

    private fun addToStepList(step: String) {
        val newList = _uiState.value.listOfSteps.toMutableList()
        newList.add(step)
        _uiState.value = _uiState.value.copy(
            listOfSteps = newList
        )
    }

    private fun addPhotoToFirebaseStorage(uri: Uri?, recipe: RecipeModel) {
        if (uri != null) {

            viewModelScope.launch {
                val riversRef = imageStorageReference.child("food_images/${uri.lastPathSegment}")
                val uploadTask = riversRef.putFile(uri)

                uploadTask.addOnFailureListener {
                    // Handle unsuccessful uploads
                }.addOnSuccessListener { taskSnapshot ->
                    taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                        _uiState.value = _uiState.value.copy(recipeImageUri = it.toString())
                        addRecipe(recipe)
                    }
                }
            }
        } else {
            _uiState.value = _uiState.value.copy(recipeImageUri = "")
            addRecipe(recipe)
        }
    }

    private fun addRecipe(recipe: RecipeModel) {
        viewModelScope.launch {
            auth.currentUser?.let {
                RecipeModel(
                    name = recipe.name,
                    description = recipe.description,
                    cookTime = recipe.cookTime,
                    ingredientsList = recipe.ingredientsList,
                    stepsList = recipe.stepsList,
                    imageUrl = _uiState.value.recipeImageUri,
                    type = recipe.type,
                    userUid = it.uid
                )
            }?.let {
                recipeRep.addRecipe(
                    it
                )
            }
        }
    }

    private fun changeSelectedTime(time: Long) {
        _uiState.value = _uiState.value.copy(selectedTimeInSeconds = time)
    }

    private fun changeNameTextValue(name: String) {
        _uiState.value = _uiState.value.copy(recipeNameTextFieldValue = name)
    }

    private fun changeDescTextValue(desc: String) {
        _uiState.value = _uiState.value.copy(recipeDescTextFieldValue = desc)
    }

    private fun changeSelectedMenuItem(item: String) {
        _uiState.value = _uiState.value.copy(selectedMenuItem = item)
    }

    private fun expandDropDownMenu() {
        _uiState.value =
            _uiState.value.copy(isDropDownMenuExpanded = !_uiState.value.isDropDownMenuExpanded)
    }

}