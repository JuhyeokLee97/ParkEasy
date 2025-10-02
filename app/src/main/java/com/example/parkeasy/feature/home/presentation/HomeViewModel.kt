package com.example.parkeasy.feature.home.presentation

import androidx.lifecycle.ViewModel
import com.example.parkeasy.feature.home.data.HomeInput
import com.example.parkeasy.feature.home.data.HomeUiState
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun handleInput(input: HomeInput) {
        when (input) {
            is HomeInput.FavoriteClicked -> showServicePreparingDialog()
            is HomeInput.DismissDialog -> dismissDialog()
            is HomeInput.UpdatedLocation -> updateLocation(input.location)
            is HomeInput.LocationPermissionGranted -> onLocationPermissionGranted()
        }
    }

    private fun showServicePreparingDialog() {
        _uiState.value = _uiState.value.copy(
            showServicePreparingDialog = true
        )
    }

    private fun dismissDialog() {
        _uiState.value = _uiState.value.copy(
            showServicePreparingDialog = false
        )
    }

    private fun updateLocation(location: LatLng) {
        _uiState.value = _uiState.value.copy(
            currentLocation = location,
            isLocationLoading = false
        )
    }

    private fun onLocationPermissionGranted() {
        _uiState.value = _uiState.value.copy(
            isLocationLoading = true
        )
    }
}