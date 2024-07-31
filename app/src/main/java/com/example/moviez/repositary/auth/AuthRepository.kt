package com.example.moviez.repositary.auth

import com.example.moviez.utils.ResponseModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
//    fun signUpWithEmailPassword(email: String, password: String): Flow<ResponseModel<String>>
//
//    fun signInWithEmailPassword(email: String, password: String): Flow<ResponseModel<String>>

    fun signInWithGoogle(idToken: String, onResult: (Boolean) -> Unit)

    fun signOut(): Flow<ResponseModel<String>>

    fun deleteAccount(): Flow<ResponseModel<String>>
}