package com.example.parkeasy.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parkeasy.core.domain.model.Result
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
    private val getParkingLotsNearbyUseCase: GetParkingLotsNearbyUseCase,
) : ViewModel() {
    private val _uiState: MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState.Loading)
    val uiState: StateFlow<HomeUIState> = _uiState.asStateFlow()

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

    fun loadNearbyParkingLots(location: LatLng) {
        val radiusInKm = 1.0
        viewModelScope.launch {
            _uiState.value = HomeUIState.Loading

            val result = getParkingLotsNearbyUseCase(
                latitude = location.latitude,
                longitude = location.longitude,
                radiusInKm = radiusInKm,
            )

            when (result) {
                is Result.Success -> {
                    _uiState.value = HomeUIState.Success(parkingLots = result.data)
                }

                is Result.Error -> {
                    // TODO Implement Error Screen
                }

                is Result.Loading -> {
                    _uiState.value = HomeUIState.Loading
                }
            }
        }
    }
}