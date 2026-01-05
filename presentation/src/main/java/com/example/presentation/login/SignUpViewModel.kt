package com.example.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.login.SignUpError
import com.example.domain.model.login.SignUpException
import com.example.domain.usecase.login.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<SignUpUiState> = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<SignUpSideEffect> = MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<SignUpSideEffect> = _sideEffect.asSharedFlow()

    fun onIdChange(id: String) {
        _uiState.update { state ->
            state.copy(id = id)
        }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { state ->
            state.copy(password = password)
        }
    }

    fun onRepeatPasswordChange(repeatPassword: String) {
        _uiState.update { state ->
            state.copy(repeatPassword = repeatPassword)
        }
    }

    fun onSignUpClick() {
        viewModelScope.launch {
            signUpUseCase(
                id = uiState.value.id,
                password = uiState.value.password,
                repeatPassword = uiState.value.repeatPassword
            ).fold(
                onSuccess = {
                    _sideEffect.emit(SignUpSideEffect.Toast("회원가입 완료"))
                    _sideEffect.emit(SignUpSideEffect.NavigateUp)
                },
                onFailure = { throwable ->
                    val errorMessage = when (throwable) {
                        is SignUpException -> {
                            when (val error = throwable.error) {
                                is SignUpError.InvalidId -> "아이디를 확인해주세요."
                                is SignUpError.PasswordMismatch -> "비밀번호가 일치하지 않습니다."
                                is SignUpError.WeakPassword -> "비밀번호는 8자 이상이어야 합니다."
                                is SignUpError.Backend -> error.message ?: "회원가입에 실패했습니다."
                                is SignUpError.Unknown -> "회원가입에 실패했습니다."
                            }
                        }

                        else -> "회원가입에 실패했습니다."
                    }
                    _sideEffect.emit(SignUpSideEffect.Toast(errorMessage))
                }
            )
        }
    }
}

data class SignUpUiState(
    val id: String = "",
    val password: String = "",
    val repeatPassword: String = "",
)

sealed interface SignUpSideEffect {
    data class Toast(val message: String) : SignUpSideEffect
    object NavigateUp : SignUpSideEffect
}