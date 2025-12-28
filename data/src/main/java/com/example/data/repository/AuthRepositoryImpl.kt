package com.example.data.repository

import com.example.domain.model.UserInfo
import com.example.domain.model.login.SignUpError
import com.example.domain.model.login.SignUpException
import com.example.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun signUp(email: String, password: String): Result<String> = withContext(Dispatchers.IO) {
        return@withContext try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result.user?.uid?.run {
                Result.success(this)
            } ?: Result.failure(SignUpException(SignUpError.Backend(message = "회원가입에 실패했습니다.")))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signIn(email: String, password: String): Result<String> = withContext(Dispatchers.IO) {
        return@withContext try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            result.user?.uid?.run {
                Result.success(this)
            } ?: Result.failure(Exception("로그인에 실패했습니다."))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout() {
        firebaseAuth.signOut()
    }

    override suspend fun getUserInfo(): UserInfo? {
        return firebaseAuth.currentUser?.run {
            UserInfo(uid = uid, email = email ?: "")
        }
    }
}