package com.example.cookbook.data.repositoryImplementation

import android.content.Context
import androidx.core.content.ContextCompat.getString
import com.example.cookbook.R
import com.example.cookbook.data.model.Response
import com.example.cookbook.data.model.UserModel
import com.example.cookbook.domain.AuthRepository
import com.example.cookbook.domain.SignInResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val userRef: FirebaseFirestore,
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

    override fun getOneUser(id: String): Flow<UserModel> = callbackFlow {
        userRef.collection("user").whereEqualTo("uid", id)
            .get()
            .addOnSuccessListener { documents ->
                val userList = documents?.toObjects(UserModel::class.java)
                if (userList != null) {
                    for (item in userList) {
                        if (item.uid == id) {
                            trySend(item)
                        }
                    }
                }
            }

        awaitClose {
            close()
        }

    }


}