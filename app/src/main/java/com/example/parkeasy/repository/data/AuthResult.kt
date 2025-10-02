package com.example.parkeasy.repository.data

sealed class AuthResult {
    data class Success(val email: String) : AuthResult()
    data class Error(val message: String) : AuthResult()
}