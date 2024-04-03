package com.example.cookbook.di

import android.content.Context
import com.example.cookbook.data.repositoryImplementation.AuthRepositoryImpl
import com.example.cookbook.data.repositoryImplementation.RecipeRepositoryImpl
import com.example.cookbook.domain.AuthRepository
import com.example.cookbook.domain.RecipeRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
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
    fun provideFirebaseRef() = FirebaseFirestore.getInstance()

    @Provides
    fun provideRecipeStorage() = Firebase.storage.reference

    @Provides
    fun provideAuthRef() = Firebase.auth

    @Provides
    fun provideRecipeRepository(): RecipeRepository = RecipeRepositoryImpl(provideFirebaseRef())

    @Provides
    fun provideAuthRepository(
        authRef: FirebaseAuth,
        @ApplicationContext appContext: Context
    ): AuthRepository = AuthRepositoryImpl(authRef, provideFirebaseRef(),  appContext)

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext appContext: Context): Context {
        return appContext
    }

}