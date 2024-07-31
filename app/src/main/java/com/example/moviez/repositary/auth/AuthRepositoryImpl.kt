package com.example.moviez.repositary.auth

import com.example.moviez.utils.ResponseModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
): AuthRepository{
//    override fun signUpWithEmailPassword(
//        email: String,
//        password: String
//    ): Flow<ResponseModel<String>> = callbackFlow{
//        trySend(ResponseModel.Loading)
//
//        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{task ->
//            if(task.isSuccessful)trySend(ResponseModel.Success("User Crested successfully"))
//            else trySend(ResponseModel.Error(task.exception?.message ?: "Unknown Error"))
//        }
//            .addOnFailureListener{
//                trySend(ResponseModel.Error(it.message?: "Unknown Error"))
//            }
//    }
//
//    override fun signInWithEmailPassword(
//        email: String,
//        password: String
//    ): Flow<ResponseModel<String>> = callbackFlow{
//        trySend(ResponseModel.Loading)
//
//        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{task ->
//            if(task.isSuccessful)trySend(ResponseModel.Success("SignedIn successfully"))
//            else trySend(ResponseModel.Error(task.exception?.message ?: "Unknown Error"))
//        }
//            .addOnFailureListener{
//                trySend(ResponseModel.Error(it.message ?: "Unknown Error"))
//            }
//    }

    override fun signInWithGoogle(idToken: String, onResult: (Boolean) -> Unit) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                onResult(task.isSuccessful)
            }
            .addOnFailureListener {
                onResult(false)
            }
    }

//        trySend(ResponseModel.Loading)
//
//        val credential = GoogleAuthProvider.getCredential(idToken, null)
//        auth.signInWithCredential(credential).addOnCompleteListener{task ->
//            if(task.isSuccessful)trySend(ResponseModel.Success("SignedIn successfully"))
//            else trySend(ResponseModel.Error(task.exception?.message ?: "Unknown Error"))
//        }
//            .addOnFailureListener{
//                trySend(ResponseModel.Error(it.message ?: "Unknown Error"))
//            }


    override fun signOut(): Flow<ResponseModel<String>> = callbackFlow{
        trySend(ResponseModel.Loading)

        try{
            auth.signOut()
            trySend(ResponseModel.Success("Signed Out Successfully"))
        }catch(ex: Exception){
            trySend(ResponseModel.Error(ex.message ?: "Unknown Error"))
        }

        awaitClose{ cancel()}
    }

    override fun deleteAccount(): Flow<ResponseModel<String>> = callbackFlow{
        trySend(ResponseModel.Loading)

        try{
            auth.currentUser?.delete()
            trySend(ResponseModel.Success("Signed Out Successfully"))
        }catch(ex: Exception){
            trySend(ResponseModel.Error(ex.message ?: "Unknown Error"))
        }

        awaitClose{ cancel()}

    }
}