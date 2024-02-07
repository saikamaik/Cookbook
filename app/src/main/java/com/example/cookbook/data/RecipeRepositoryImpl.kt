package com.example.cookbook.data

import com.example.cookbook.domain.AddRecipeResponse
import com.example.cookbook.domain.RecipeRepository
import com.example.cookbook.model.RecipeModel
import com.example.cookbook.model.Response
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepositoryImpl @Inject constructor(
    private val recipeRef: CollectionReference
): RecipeRepository {

    override suspend fun addRecipe(recipe: RecipeModel): AddRecipeResponse = try {
        recipeRef.document(recipe.name).set(recipe).await()
        Response.Success(true)
    }
    catch (e: Exception) {
        Response.Failure(e)
    }

    override fun deleteRecipe(recipe: RecipeModel) {
        TODO("Not yet implemented")
    }

    override fun getAllRecipes() = callbackFlow {
        val snapshotListener = recipeRef
            .addSnapshotListener { snapshot, e ->
                val recipeResponse = if (snapshot!=null) {
                    val recipe = snapshot.toObjects(RecipeModel::class.java)
                    Response.Success(recipe)
                } else {
                    Response.Failure(e)
                }
                trySend(recipeResponse)
            }
        awaitClose {
            snapshotListener.remove()
        }

    }


}