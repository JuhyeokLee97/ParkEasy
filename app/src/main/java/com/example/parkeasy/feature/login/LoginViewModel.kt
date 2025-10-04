package com.example.parkeasy.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parkeasy.repository.AuthRepository
import com.example.parkeasy.repository.data.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _formState: MutableStateFlow<LoginOutput.FormState> = MutableStateFlow(LoginOutput.FormState())
    private val _uiState: MutableStateFlow<LoginOutput.UiState> = MutableStateFlow(LoginOutput.UiState())
    private val _sideEffect: MutableSharedFlow<LoginOutput.SideEffect> = MutableSharedFlow<LoginOutput.SideEffect>(replay = 0)
    val sideEffect: SharedFlow<LoginOutput.SideEffect>
        get() = _sideEffect

    val uiOutput: Flow<LoginOutput> = combine(
        _formState,
        _uiState
    ) { formState, uiState ->
        LoginOutput(
            formState = formState,
            uiState = uiState.copy(
                isLoginEnabled = formState.id.isNotBlank() && formState.password.isNotBlank()
            )
        )
    }

    fun handleInput(input: LoginInput) {
        when (input) {
            is LoginInput.UpdateId -> updateId(input.id)
            is LoginInput.UpdatePassword -> updatePassword(input.password)
            is LoginInput.LoginClicked -> handleLogin()
            is LoginInput.FindIdPasswordClicked -> showServicePreparing()
            is LoginInput.SignupClicked -> handleSignupClicked()
            is LoginInput.DismissDialog -> dismissDialog()
        }
    }

    private fun updateId(id: String) {
        _formState.value = _formState.value.copy(id = id)
    }

    private fun updatePassword(password: String) {
        _formState.value = _formState.value.copy(password = password)
    }

    private fun handleLogin() {
        val currentForm = _formState.value
        if (currentForm.id.isBlank() || currentForm.password.isBlank()) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )
            when (val result = authRepository.signIn(
                email = currentForm.id,
                password = currentForm.password
            )) {
                is AuthResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = null
                    )
                    _sideEffect.emit(LoginOutput.SideEffect.NavigateToHome)
                }
                is AuthResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    private fun handleSignupClicked() {
        viewModelScope.launch {
            _sideEffect.emit(LoginOutput.SideEffect.NavigateToSignUp)
        }
    }
    private fun showServicePreparing() {
        _uiState.value = _uiState.value.copy(showServicePreparingDialog = true)
    }

    private fun dismissDialog() {
        _uiState.value = _uiState.value.copy(showServicePreparingDialog = false)
    }
}