package com.example.parkeasy.feature.arround

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parkeasy.feature.arround.data.ParkingLotState
import com.example.parkeasy.feature.arround.data.input.AroundParkViewModelInput
import com.example.parkeasy.feature.arround.data.output.AroundParkViewModelOutput
import com.example.parkeasy.feature.arround.domain.usecase.GetAroundParkingLotsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AroundParkViewModel @Inject constructor(
    private val getAroundParkingLotsUseCase: GetAroundParkingLotsUseCase
) : ViewModel(), AroundParkViewModelInput, AroundParkViewModelOutput {

    val input: AroundParkViewModelInput = this
    val output: AroundParkViewModelOutput = this

    private val _parkingLotsState: MutableStateFlow<ParkingLotState> = MutableStateFlow(ParkingLotState.Loading)
    override val parkingLotsState: StateFlow<ParkingLotState>
        get() = _parkingLotsState

    fun init() {
        fetchParkingLots()
    }

    private fun fetchParkingLots() {
        viewModelScope.launch {
            _parkingLotsState.value = ParkingLotState.Loading
            val parkingLots = getAroundParkingLotsUseCase.invoke()

            _parkingLotsState.value = ParkingLotState.Loaded(parkingLots)
        }
    }
}