package com.example.parkeasy.repository

import com.example.parkeasy.repository.data.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override suspend fun signUp(email: String, password: String): AuthResult {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            AuthResult.Success(email = result.user?.email ?: "")
        } catch (e: Exception) {
            AuthResult.Error(message = e.message ?: "회원가입에 실패했습니다.")
        }
    }

    override suspend fun signIn(email: String, password: String): AuthResult {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            AuthResult.Success(email = result.user?.email ?: "")
        } catch(e : Exception) {
            AuthResult.Error(message = e.message ?: "로그인에 실패했습니다.")
        }
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }

    override fun getCurrentUserEmail(): String? {
        return firebaseAuth.currentUser?.email
    }
}