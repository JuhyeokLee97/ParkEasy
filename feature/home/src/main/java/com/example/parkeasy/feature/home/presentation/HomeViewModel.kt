package com.example.parkeasy.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parkeasy.core.domain.model.getOrElse
import com.example.parkeasy.core.domain.repository.LocationRepository
import com.example.parkeasy.core.domain.usecase.GetParkingLotsNearbyUseCase
import com.example.parkeasy.feature.home.model.DialogState
import com.example.parkeasy.feature.home.model.HomeUIState
import com.example.parkeasy.feature.home.model.ServicePreparingDialogState
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    private val getParkingLotsNearbyUseCase: GetParkingLotsNearbyUseCase,
) : ViewModel() {
    private val _uiState: MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState.Loading)
    val uiState: StateFlow<HomeUIState> = _uiState.asStateFlow()

    fun loadNearbyParkingLots(radiusInKm: Double = 5.0) {
        viewModelScope.launch {
            _uiState.value = HomeUIState.Loading

            val location = locationRepository.getCurrentLocation()
                .getOrElse { error ->
                    _uiState.value = HomeUIState.Error(
                        exception = error.exception,
                        message = error.message ?: "",
                    )
                    return@launch
                }

            val parkingLots = getParkingLotsNearbyUseCase(
                latitude = location.latitude,
                longitude = location.longitude,
                radiusInKm = radiusInKm,
            ).getOrElse { error ->
                _uiState.value = HomeUIState.Error(
                    exception = error.exception,
                    message = error.message ?: "주변 주차장을 가져오는데 실패했습니다.",
                )
                return@launch
            }

            _uiState.value = HomeUIState.Success(
                parkingLots = parkingLots,
                currentLocation = location,
            )
        }
    }

    private fun showServicePreparingDialog() {
        _uiState.update { currentState ->
            if (currentState is HomeUIState.Success) {
                currentState.copy(dialogState = DialogState(ServicePreparingDialogState.Visible))
            } else {
                currentState
            }

        }
    }

    private fun dismissDialog() {
        _uiState.update { currentState ->
            if (currentState is HomeUIState.Success) {
                currentState.copy(dialogState = DialogState())
            } else {
                currentState
            }
        }
    }

    private fun updateLocation(location: LatLng) {

    }
}