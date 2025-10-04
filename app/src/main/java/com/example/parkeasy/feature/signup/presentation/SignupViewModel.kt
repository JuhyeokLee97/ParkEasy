package com.example.parkeasy.feature.signup.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parkeasy.feature.signup.data.SignupOutput
import com.example.parkeasy.repository.AuthRepository
import com.example.parkeasy.repository.data.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    private val _sideEffect: MutableSharedFlow<SignupOutput.SideEffect> = MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<SignupOutput.SideEffect> = _sideEffect.asSharedFlow()

    fun requestSignup(id: String, pw: String) {
        viewModelScope.launch {
            when(val result = authRepository.signUp(id, pw)) {
                is AuthResult.Success -> {
                    _sideEffect.emit(SignupOutput.SideEffect.NavigateUp)
                }
                is AuthResult.Error -> {
                    _sideEffect.emit(SignupOutput.SideEffect.SignupError(error = result.message))
                }
            }

        }
    }
}