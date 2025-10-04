package com.example.parkeasy.feature.signup.data

data class SignupOutput(
    val uiState: UiState
) {
    data class UiState(
        val isLoading: Boolean = false,
    )

    sealed interface SideEffect {
        object NavigateUp : SideEffect
        data class SignupError(val error: String) : SideEffect
    }
}