package com.example.presentation.main.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.main.mypage.GetUserInfoUseCase
import com.example.domain.usecase.main.mypage.LogoutUseCase
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
class MyPageViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<MyPageUiState> = MutableStateFlow(MyPageUiState())
    val uiState: StateFlow<MyPageUiState> = _uiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<MyPageSideEffect> = MutableSharedFlow(
        replay = 0,
        extraBufferCapacity = 1
    )
    val sideEffect: SharedFlow<MyPageSideEffect> = _sideEffect.asSharedFlow()

    init {
        loadUserInfo()
    }

    private fun loadUserInfo() {
        viewModelScope.launch {
            getUserInfoUseCase().fold(
                onSuccess = { userInfo ->
                    _uiState.update { state ->
                        state.copy(email = userInfo.email)
                    }
                },
                onFailure = {
                    _sideEffect.emit(MyPageSideEffect.Toast("유저 정보를 가져오는데 실패했습니다."))
                }
            )
        }
    }

    fun onLogoutClick() {
        viewModelScope.launch {
            logoutUseCase()
            _sideEffect.emit(MyPageSideEffect.Logout)
        }
    }
}

data class MyPageUiState(
    val email: String = "",
)

sealed interface MyPageSideEffect {
    data class Toast(val message: String) : MyPageSideEffect
    object Logout : MyPageSideEffect
}