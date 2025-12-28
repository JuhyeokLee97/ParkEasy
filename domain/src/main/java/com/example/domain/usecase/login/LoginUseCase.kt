package com.example.domain.usecase.login

import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    val authRepository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return authRepository.signIn(email, password).fold(
            onSuccess = { uid ->
                // TODO Set uid For 자동 로그인
                Result.success(Unit)
            },
            onFailure = {
                Result.failure(Exception("로그인에 실패했습니다."))
            }
        )
    }
}