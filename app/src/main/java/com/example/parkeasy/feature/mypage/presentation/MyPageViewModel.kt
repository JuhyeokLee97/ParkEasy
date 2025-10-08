package com.example.parkeasy.feature.mypage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parkeasy.feature.mypage.presentation.input.MyPageInput
import com.example.parkeasy.feature.mypage.presentation.output.MyPageOutput
import com.example.parkeasy.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<MyPageOutput.UiState> = MutableStateFlow(
        MyPageOutput.UiState(
            userEmail = authRepository.getCurrentUserEmail()
        )
    )
    private val _sideEffect: MutableSharedFlow<MyPageOutput.SideEffect?> = MutableSharedFlow()

    val output: Flow<MyPageOutput> = combine(
        _uiState,
        _sideEffect.onStart { emit(null) }
    ) { uiState, sideEffect ->
        MyPageOutput(
            uiState = uiState,
            sideEffect = sideEffect
        )
    }

    fun handleInput(input: MyPageInput) {
        when (input) {
            is MyPageInput.NavigateToCarInfo -> handleNavigateToCarInfo()
            is MyPageInput.NavigateToPaymentInfo -> handleNavigateToPaymentInfo()
            is MyPageInput.NavigateToReservationHistory -> handleNavigateToReservationHistory()
            is MyPageInput.NavigateToSetting -> handleNavigateToSettings()
            is MyPageInput.Logout -> handleLogout()
            is MyPageInput.NavigateUp -> handleDismissDialog()
        }
    }

    private fun handleNavigateToCarInfo() {
        viewModelScope.launch {
            _sideEffect.emit(MyPageOutput.SideEffect.NavigateToCarInfo)
        }
    }

    private fun handleNavigateToPaymentInfo() {
        viewModelScope.launch {
            _sideEffect.emit(MyPageOutput.SideEffect.NavigateToPaymentInfo)
        }
    }

    private fun handleNavigateToReservationHistory() {
        _uiState.value = _uiState.value.copy(
            showServicePreparingDialog = true
        )
    }

    private fun handleNavigateToSettings() {
        _uiState.value = _uiState.value.copy(
            showServicePreparingDialog = true
        )
    }

    private fun handleLogout() {
        viewModelScope.launch {
            authRepository.signOut()
            _sideEffect.emit(MyPageOutput.SideEffect.NavigateToLogin)
        }
    }

    private fun handleDismissDialog() {
        _uiState.value = _uiState.value.copy(
            showServicePreparingDialog = false
        )
    }
}