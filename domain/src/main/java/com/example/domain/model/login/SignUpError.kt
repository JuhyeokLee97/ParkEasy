package com.example.domain.model.login

sealed interface SignUpError {
    data object InvalidId : SignUpError
    data object PasswordMismatch : SignUpError
    data object WeakPassword : SignUpError
    data class Backend(val code: Int? = null, val message: String? = null) : SignUpError
    data object Unknown : SignUpError
}

class SignUpException(val error: SignUpError) : RuntimeException()
