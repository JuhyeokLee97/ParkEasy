package com.example.parkeasy.feature.around.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parkeasy.core.domain.model.getOrElse
import com.example.parkeasy.core.domain.repository.LocationRepository
import com.example.parkeasy.core.domain.usecase.GetParkingLotsNearbyUseCase
import com.example.parkeasy.feature.around.model.AroundUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AroundViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    private val getParkingLotsNearbyUseCase: GetParkingLotsNearbyUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<AroundUiState> = MutableStateFlow(AroundUiState.Loading)
    val uiState: StateFlow<AroundUiState> = _uiState

    fun fetchParkingLots(radiusInKm: Double = 5.0) {
        viewModelScope.launch {
            _uiState.value = AroundUiState.Loading

            val location = locationRepository.getCurrentLocation()
                .getOrElse { error ->
                    _uiState.value = AroundUiState.Error(message = error.message ?: "주변 위치를 가져오는데 실패했습니다.")
                    return@launch
                }

            val parkingLot = getParkingLotsNearbyUseCase(
                latitude = location.latitude,
                longitude = location.longitude,
                radiusInKm = radiusInKm,
            ).getOrElse { error ->
                _uiState.value = AroundUiState.Error(message = error.message ?: "주변 주차장을 가져오는데 실패했습니다.")
                return@launch
            }

            _uiState.value = AroundUiState.Success(parkingLots = parkingLot)
        }
    }
}