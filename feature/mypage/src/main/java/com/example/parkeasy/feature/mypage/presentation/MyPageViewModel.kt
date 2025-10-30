package com.example.parkeasy.feature.mypage.presentation

import androidx.lifecycle.ViewModel
import com.example.parkeasy.core.domain.repository.AuthRepository
import com.example.parkeasy.feature.mypage.model.MyPageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    val authRepository: AuthRepository
) : ViewModel() {

    private val _uiSate: MutableStateFlow<MyPageUiState> = MutableStateFlow(MyPageUiState.Loading)
    val uiState: StateFlow<MyPageUiState> = _uiSate

    fun loadProfileInfo() {
        _uiSate.value = MyPageUiState.Success(
            userEmail = authRepository.getCurrentUserEmail()
        )
    }
}