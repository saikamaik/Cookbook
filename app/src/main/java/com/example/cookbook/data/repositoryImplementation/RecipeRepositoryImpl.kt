package com.example.cookbook.data.repositoryImplementation

import com.example.cookbook.data.model.RecipeModel
import com.example.cookbook.data.model.Response
import com.example.cookbook.domain.AddRecipeResponse
import com.example.cookbook.domain.RecipeRepository
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepositoryImpl @Inject constructor(
    private val recipeRef: FirebaseFirestore
) : RecipeRepository {

    override suspend fun addRecipe(recipe: RecipeModel): AddRecipeResponse = try {
        recipeRef.collection("recipe").add(recipe).await()
        Response.Success(true)
    } catch (e: Exception) {
        Response.Failure(e.message)
    }

    override fun deleteRecipe(recipe: RecipeModel) {
        TODO("Not yet implemented")
    }

    override fun getAllRecipes() = callbackFlow {
        val snapshotListener = recipeRef.collection("recipe")
            .addSnapshotListener { snapshot, e ->
                val recipeResponse = if (snapshot != null) {
                    val recipe = snapshot.toObjects(RecipeModel::class.java)
                    Response.Success(recipe)
                } else {
                    Response.Failure(e?.message)
                }
                trySend(recipeResponse)
            }
        awaitClose {
            snapshotListener.remove()
        }

    }

    override fun getOneRecipe(id: String): Flow<RecipeModel> = callbackFlow {
        recipeRef.collection("recipe").whereEqualTo(FieldPath.documentId(), id)
            .get()
            .addOnSuccessListener { documents ->
                val recipeList = documents?.toObjects(RecipeModel::class.java)
                if (recipeList != null) {
                    for (item in recipeList) {
                        if (item.id == id) {
                            trySend(item)
                        }
                    }
                }
            }

        awaitClose {
            close()
        }
    }

    override suspend fun updateRecipe(recipe: RecipeModel) = try {
        recipeRef.document(recipe.name).set(recipe).await()
        Response.Success(true)
    } catch (e: Exception) {
        Response.Failure(e.message)
    }

}