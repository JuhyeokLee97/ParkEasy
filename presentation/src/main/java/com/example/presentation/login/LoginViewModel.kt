package com.example.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<LoginSideEffect> = MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<LoginSideEffect> = _sideEffect

    fun onIdChange(id: String) {
        updateState(id = id)
    }

    fun onPasswordChange(password: String) {
        updateState(password = password)
    }

    private fun updateState(
        id: String = uiState.value.id,
        password: String = uiState.value.password,
    ) {
        _uiState.update { state ->
            state.copy(
                id = id,
                password = password,
                isLoginEnabled = id.isNotEmpty() && password.isNotEmpty()
            )
        }
    }

    fun onLoginClick() {
        viewModelScope.launch {
            loginUseCase(
                email = uiState.value.id,
                password = uiState.value.password
            ).fold(
                onSuccess = {
//                    _sideEffect.emit(LoginSideEffect.Toast("로그인에 성공했습니다."))
                    _sideEffect.emit(LoginSideEffect.NavigateToHome)
                },
                onFailure = {
                    _sideEffect.emit(LoginSideEffect.Toast("로그인에 실패했습니다."))
                }
            )
        }
    }
}

data class LoginUiState(
    val id: String = "",
    val password: String = "",
    val isLoginEnabled: Boolean = false
)

sealed interface LoginSideEffect {
    data class Toast(val message: String) : LoginSideEffect
    object NavigateToHome : LoginSideEffect
}
