package com.example.parkeasy.feature.arround.data.output

import com.example.parkeasy.feature.arround.data.ParkingLotState
import kotlinx.coroutines.flow.StateFlow

interface AroundParkViewModelOutput {
    val parkingLotsState: StateFlow<ParkingLotState>
}