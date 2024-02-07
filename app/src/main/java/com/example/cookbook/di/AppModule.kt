package com.example.cookbook.di

import com.example.cookbook.data.RecipeRepositoryImpl
import com.example.cookbook.domain.RecipeRepository
import com.example.cookbook.useCase.GetRecipe
import com.example.cookbook.useCase.UseCases
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRecipeRef() = Firebase.firestore.collection("recipe")

    @Provides
    fun provideRecipeRepository(
        recipeRef: CollectionReference
    ): RecipeRepository = RecipeRepositoryImpl(recipeRef)

    @Provides
    fun provideUseCases(
        repo: RecipeRepository
    ) = UseCases(
        getRecipe = GetRecipe(repo)
    )
}