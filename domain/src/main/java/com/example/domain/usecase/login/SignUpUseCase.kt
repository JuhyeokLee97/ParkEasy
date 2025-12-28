package com.example.domain.usecase.login

import com.example.domain.model.login.SignUpError
import com.example.domain.model.login.SignUpException
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    private val EMAIL_REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")

    suspend operator fun invoke(id: String, password: String, repeatPassword: String): Result<String> {
        if (!isValidEmail(id)) return Result.failure(SignUpException(SignUpError.InvalidId))
        if (password != repeatPassword) return Result.failure(SignUpException(SignUpError.PasswordMismatch))
        if (password.length < 8) return Result.failure(SignUpException(SignUpError.WeakPassword))

        return authRepository.signUp(id, password)
    }

    private fun isValidEmail(email: String): Boolean = EMAIL_REGEX.matches(email)
}
