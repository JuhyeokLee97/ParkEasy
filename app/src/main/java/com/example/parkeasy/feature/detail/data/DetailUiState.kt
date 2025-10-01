package com.example.parkeasy.feature.detail.data

sealed interface DetailUiState {
    object Loading : DetailUiState
    data class Success(val parkingLot: ParkingLotDetailEntity) : DetailUiState
    data class Error(val message: String) : DetailUiState
}