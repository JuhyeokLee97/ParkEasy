package com.example.presentation.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Location
import com.example.domain.usecase.main.home.GetCurrentLocationUseCase
import com.google.android.gms.maps.model.LatLng
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
class HomeViewModel @Inject constructor(
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<HomeSideEffect> = MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<HomeSideEffect> = _sideEffect.asSharedFlow()

    fun loadCurrentLocation() {
        viewModelScope.launch {
            getCurrentLocationUseCase().fold(
                onSuccess = { location ->
                    _uiState.update {
                        it.copy(currentLocation = location.run { LatLng(latitude, longitude) })
                    }
                },
                onFailure = { exception ->
                    val message = when (exception) {
                        is SecurityException -> "위치 권한이 필요합니다."
                        else -> "위치를 가져올 수 없습니다."
                    }
                    _sideEffect.emit(HomeSideEffect.Toast(message))
                }
            )
        }
    }

    fun useDefaultLocation() {
        _uiState.update {
            it.copy(currentLocation = Location.DEFAULT.run { LatLng(latitude, longitude) })
        }
    }
}

data class HomeUiState(
    val currentLocation: LatLng = Location.DEFAULT.run { LatLng(latitude, longitude) },
)

sealed interface HomeSideEffect {
    data class Toast(val message: String) : HomeSideEffect
}