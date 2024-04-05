package com.example.cookbook.data.repositoryImplementation

import com.example.cookbook.data.model.RecipeModel
import com.example.cookbook.data.model.Response
import com.example.cookbook.data.model.SavedRecipesModel
import com.example.cookbook.domain.AddRecipeResponse
import com.example.cookbook.domain.RecipeRepository
import com.example.cookbook.domain.RecipeResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepositoryImpl @Inject constructor(
    private val recipeRef: FirebaseFirestore,
    private val auth: FirebaseAuth
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

    override fun getSearchedRecipes(query: String): Flow<RecipeResponse> = callbackFlow {

        recipeRef.collection("recipe")
            .where(
                Filter.and(
                    Filter.greaterThanOrEqualTo("name", query),
                    Filter.lessThanOrEqualTo("name", query + "\uf8ff")
                )
            )
            .get()
            .addOnSuccessListener { documents ->
                val searchedRecipeList = documents?.toObjects(RecipeModel::class.java)
                if (searchedRecipeList != null) {
                    trySend(Response.Success(searchedRecipeList))
                }
            }.addOnFailureListener {
                trySend(Response.Failure(it.message))
            }

        awaitClose {
            close()
        }
    }

        //пока не используется
    override suspend fun updateRecipe(recipe: RecipeModel) = try {
        recipeRef.document(recipe.name).set(recipe).await()
        Response.Success(true)
    } catch (e: Exception) {
        Response.Failure(e.message)
    }

    override fun addRecipeToBookmark(recipeId: String, userId: String): Flow<Response<Boolean>> =
        callbackFlow {
            val bookmarkRecipe = SavedRecipesModel(userId = userId, recipeId = recipeId)
            try {
                recipeRef.collection("saved_recipe").add(bookmarkRecipe)
                    .await()
                Response.Success(true)
            } catch (e: Exception) {
                Response.Failure(e.message)
            }

            awaitClose {
                close()
            }
        }

    override fun getAllBookmarkedRecipes(userId: String): Flow<RecipeResponse> = callbackFlow {

        recipeRef.collection("saved_recipe")
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { documents ->
                val savedRecipeList = documents?.toObjects(SavedRecipesModel::class.java)
                if (savedRecipeList != null) {
                    if (savedRecipeList.isNotEmpty()) {
                        val list = savedRecipeList.map {
                            it.recipeId
                        }
                        recipeRef.collection("recipe")
                            .whereIn(FieldPath.documentId(), list)
                            .get()
                            .addOnSuccessListener { recipesDocuments ->
                                val recipes = recipesDocuments?.toObjects(RecipeModel::class.java)
                                if (recipes != null) {
                                    trySend(Response.Success(recipes))
                                }
                            }
                    } else {
                        trySend(Response.Failure(e = null))
                    }
                }
            }

        awaitClose {
            close()
        }
    }

    override fun deleteBookmarkRecipe(recipeId: String) {

        recipeRef.collection("saved_recipe")
            .where(Filter.and(
                Filter.equalTo("userId", auth.currentUser?.uid),
                Filter.equalTo("recipeId", recipeId)
            ))
            .get()
            .addOnSuccessListener { documents ->
                val deletedRecipes = documents?.toObjects(SavedRecipesModel::class.java)

                if (deletedRecipes != null){
                    for (recipe in deletedRecipes) {
                        recipeRef.collection("saved_recipe").document(recipe.id).delete()
                    }
                }
            }
    }

}