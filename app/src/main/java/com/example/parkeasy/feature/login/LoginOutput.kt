package com.example.parkeasy.feature.login

data class LoginOutput(
    val formState: FormState = FormState(),
    val uiState: UiState = UiState(),
    val sideEffect: SideEffect? = null
) {
    data class FormState(
        val id: String = "",
        val password: String = ""
    )

    data class UiState(
        val showServicePreparingDialog: Boolean = false,
        val isLoading: Boolean = false,
        val isLoginEnabled: Boolean = false,
        val errorMessage: String? = null,
    )

    sealed class SideEffect {
        object NavigateToHome : SideEffect()
        object NavigateToSignUp : SideEffect()
    }
}