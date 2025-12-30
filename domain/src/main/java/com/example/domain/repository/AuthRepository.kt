package com.example.domain.repository

import com.example.domain.model.UserInfo

interface AuthRepository {

    suspend fun signUp(email: String, password: String): Result<String>

    suspend fun signIn(email: String, password: String): Result<String>

    suspend fun logout()

    suspend fun getUserInfo(): UserInfo?
}