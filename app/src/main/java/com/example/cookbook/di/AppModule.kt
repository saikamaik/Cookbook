package com.example.cookbook.di

import android.content.Context
import com.example.cookbook.data.AuthRepositoryImpl
import com.example.cookbook.data.RecipeRepositoryImpl
import com.example.cookbook.domain.AuthRepository
import com.example.cookbook.domain.RecipeRepository
import com.example.cookbook.useCase.GetRecipe
import com.example.cookbook.useCase.UseCases
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRecipeRef() = Firebase.firestore.collection("recipe")

//    @Provides
//    fun provideSavedRecipesRef() = Firebase.firestore.collection("saved_recipe")

    @Provides
    fun provideRecipeStorage() = Firebase.storage.reference

    @Provides
    fun provideAuthRef() = Firebase.auth

    @Provides
    fun provideRecipeRepository(
        recipeRef: CollectionReference
    ): RecipeRepository = RecipeRepositoryImpl(recipeRef)

    @Provides
    fun provideAuthRepository(
        authRef: FirebaseAuth,
        @ApplicationContext appContext: Context
    ): AuthRepository = AuthRepositoryImpl(authRef, appContext)

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext appContext: Context): Context {
        return appContext
    }

    @Provides
    fun provideUseCases(
        repo: RecipeRepository
    ) = UseCases(
        getRecipe = GetRecipe(repo)
    )

}