package com.example.parkeasy.feature.around.data.output

import com.example.parkeasy.feature.around.data.ParkingLotState
import kotlinx.coroutines.flow.StateFlow

interface AroundParkViewModelOutput {
    val parkingLotsState: StateFlow<ParkingLotState>
}