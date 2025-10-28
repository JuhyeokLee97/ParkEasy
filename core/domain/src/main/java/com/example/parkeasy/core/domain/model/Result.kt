package com.example.parkeasy.core.domain.model

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(
        val exception: Throwable,
        val message: String? = null
    ) : Result<Nothing>
    object Loading: Result<Nothing>
}

inline fun <T> Result<T>.getOrElse(onError: (Result.Error) -> Nothing): T {
    return when(this) {
        is Result.Success -> data
        is Result.Error -> onError(this)
        is Result.Loading -> throw IllegalStateException("Result is still Loading")
    }
}