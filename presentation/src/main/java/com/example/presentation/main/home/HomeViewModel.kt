package com.example.presentation.main.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Location
import com.example.domain.model.ParkingLot
import com.example.domain.usecase.main.home.GetCurrentLocationUseCase
import com.example.domain.usecase.main.home.GetParkingLotsUseCase
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
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val getParkingLotsUseCase: GetParkingLotsUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<HomeSideEffect> = MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<HomeSideEffect> = _sideEffect.asSharedFlow()

    fun loadCurrentLocation() {
        viewModelScope.launch {
            getCurrentLocationUseCase().fold(
                onSuccess = { location ->
                    updateCurrentLocationState(location)
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
        updateCurrentLocationState()
    }

    private fun updateCurrentLocationState(location: Location = Location.DEFAULT) {
        _uiState.update {
            it.copy(currentLocation = location.run { LatLng(latitude, longitude) })
        }
        loadParkingLots()
    }

    private fun loadParkingLots() {
        viewModelScope.launch {
            getParkingLotsUseCase(
                latitude = uiState.value.currentLocation.latitude,
                longitude = uiState.value.currentLocation.longitude
            ).fold(
                onSuccess = { parkingLots ->
                    _uiState.update {
                        it.copy(parkingLots = parkingLots)
                    }
                },
                onFailure = {
                    _sideEffect.emit(HomeSideEffect.Toast("주차장 목록을 가져오는데 실패했습니다."))
                }
            )
        }
    }
}

data class HomeUiState(
    val currentLocation: LatLng = Location.DEFAULT.run { LatLng(latitude, longitude) },
    val parkingLots: List<ParkingLot> = emptyList(),
)

sealed interface HomeSideEffect {
    data class Toast(val message: String) : HomeSideEffect
}