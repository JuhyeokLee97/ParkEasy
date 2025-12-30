package com.example.presentation.login

sealed class LoginRoute(
    val name: String
) {
    object LoginScreen : LoginRoute("LoginScreen")
    object SignUpScreen : LoginRoute("SignUpScreen")
}