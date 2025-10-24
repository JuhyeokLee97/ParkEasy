package com.example.parkeasy.core.domain.repository

import com.example.parkeasy.core.domain.model.Result

interface AuthRepository {
    suspend fun signUp(email: String, password: String): Result<String>
    suspend fun signIn(email: String, password: String): Result<String>
    suspend fun signOut(): Result<Unit>
    fun getCurrentUserEmail(): String?
}