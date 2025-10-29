package com.example.parkeasy.feature.around.model

import com.example.parkeasy.core.domain.model.ParkingLot

interface AroundUiState {

    object Loading : AroundUiState

    data class Success(
        val parkingLots: List<ParkingLot>
    ) : AroundUiState

    data class Error(
        val message: String
    ) : AroundUiState
}