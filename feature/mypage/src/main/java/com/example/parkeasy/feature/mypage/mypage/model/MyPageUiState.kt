package com.example.parkeasy.feature.mypage.mypage.model

sealed interface MyPageUiState {
    object Loading : MyPageUiState

    data class Success(
        val userEmail: String?,
        val showServicePreparingDialog: Boolean = false,
    ) : MyPageUiState

    data class Error(
        val message: String
    ) : MyPageUiState
}