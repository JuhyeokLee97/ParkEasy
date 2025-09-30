package com.example.parkeasy.feature.login

sealed class LoginInput {
    data class UpdateId(val id: String): LoginInput()
    data class UpdatePassword(val password: String): LoginInput()
    object LoginClicked: LoginInput()
    object FindIdPasswordClicked: LoginInput()
    object SignupClicked: LoginInput()
    object DismissDialog: LoginInput()
}