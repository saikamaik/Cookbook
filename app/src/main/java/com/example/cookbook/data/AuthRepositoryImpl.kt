package com.example.cookbook.data

import android.content.Context
import androidx.core.content.ContextCompat.getString
import com.example.cookbook.R
import com.example.cookbook.data.model.Response
import com.example.cookbook.domain.AuthRepository
import com.example.cookbook.domain.SignInResponse
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    @ApplicationContext val context: Context
) : AuthRepository {

    override fun signInWithEmailAndPassword(
        email: String, password: String
    ): Flow<SignInResponse> = callbackFlow {
        trySend(Response.Loading)

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                trySend(Response.Success(true))
            } else {
                trySend(Response.Failure(e = getString(context, R.string.wrong_cred)))
            }
        }

        awaitClose {
            close()
        }
    }
}