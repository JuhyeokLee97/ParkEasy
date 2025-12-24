package com.example.domain.repository.login

interface AuthRepository {

    suspend fun signUp(email: String, password: String): Result<String>

    suspend fun signIn(email: String, password: String): Result<String>
}