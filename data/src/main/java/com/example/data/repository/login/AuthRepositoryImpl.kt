package com.example.data.repository.login

import com.example.domain.model.login.SignUpError
import com.example.domain.model.login.SignUpException
import com.example.domain.repository.login.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun signUp(email: String, password: String): Result<String> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result.user?.uid?.run {
                Result.success(this)
            } ?: Result.failure(SignUpException(SignUpError.Backend(message = "회원가입에 실패했습니다.")))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}