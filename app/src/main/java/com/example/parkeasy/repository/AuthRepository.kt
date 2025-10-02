package com.example.parkeasy.repository

import com.example.parkeasy.repository.data.AuthResult

interface AuthRepository {
    suspend fun signUp(email: String, password: String): AuthResult
    suspend fun signIn(email: String, password: String): AuthResult
    suspend fun signOut()
    fun getCurrentUserEmail(): String?
}