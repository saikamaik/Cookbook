package com.example.cookbook.domain

import com.example.cookbook.data.model.Response
import com.example.cookbook.data.model.UserModel
import kotlinx.coroutines.flow.Flow

typealias SignInResponse = Response<Boolean>

interface AuthRepository {

    fun signInWithEmailAndPassword(email: String, password: String): Flow<SignInResponse>

    fun getOneUser(id: String): Flow<UserModel>

}