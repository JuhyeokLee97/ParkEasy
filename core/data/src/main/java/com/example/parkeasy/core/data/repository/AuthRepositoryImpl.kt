package com.example.parkeasy.core.data.repository

import com.example.parkeasy.core.domain.model.Result
import com.example.parkeasy.core.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override suspend fun signUp(email: String, password: String): Result<String> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Result.Success(result.user?.email ?: "")
        } catch (e: Exception) {
            Result.Error(exception = e, message = e.message ?: "회원가입에 실패했습니다.")
        }
    }

    override suspend fun signIn(email: String, password: String): Result<String> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.Success(result.user?.email ?: "")
        } catch (e: Exception) {
            Result.Error(exception = e, message = e.message ?: "로그인에 실패했습니다.")
        }
    }

    override suspend fun signOut(): Result<Unit> {
        return try {
            firebaseAuth.signOut()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(exception = e, message = e.message ?: "로그아웃에 실패했습니다.")
        }
    }

    override fun getCurrentUserEmail(): String? {
        return firebaseAuth.currentUser?.email
    }
}