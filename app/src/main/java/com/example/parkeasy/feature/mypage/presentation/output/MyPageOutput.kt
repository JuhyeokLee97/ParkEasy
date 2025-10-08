package com.example.parkeasy.feature.mypage.presentation.output

// UI 상태
data class MyPageOutput(
    val uiState: UiState,
    val sideEffect: SideEffect?
) {

    data class UiState(
        val showServicePreparingDialog: Boolean = false,
        val userEmail: String? = null,
    )

    sealed class SideEffect {
        object NavigateToCarInfo : SideEffect()
        object NavigateToPaymentInfo : SideEffect()
        object NavigateToReservationHistory : SideEffect()
        object NavigateToSettings : SideEffect()
        object NavigateToLogin : SideEffect()
        object NavigateUp : SideEffect()
    }
}
