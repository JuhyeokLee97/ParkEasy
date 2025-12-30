package com.example.presentation.main.aroundlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ParkingLot
import com.example.domain.usecase.main.aroundlist.GetAroundParkingLotListUseCase
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
class AroundListViewModel @Inject constructor(
    private val getAroundParkingLotListUseCase: GetAroundParkingLotListUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<AroundListUiState> = MutableStateFlow(AroundListUiState(emptyList()))
    val uiState: StateFlow<AroundListUiState> = _uiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<AroundListSideEffect> = MutableSharedFlow<AroundListSideEffect>(replay = 0)
    val sideEffect: SharedFlow<AroundListSideEffect> = _sideEffect.asSharedFlow()

    init {
        loadAroundParkingLots()
    }

    private fun loadAroundParkingLots() {
        viewModelScope.launch {
            getAroundParkingLotListUseCase().fold(
                onSuccess = { parkingLots ->
                    _uiState.update { state ->
                        state.copy(parkingLots = parkingLots)
                    }
                },
                onFailure = {
                    _sideEffect.emit(AroundListSideEffect.Toast("목록을 읽어오는데 실패했습니다"))
                }
            )
        }
    }
}

data class AroundListUiState(
    val parkingLots: List<ParkingLot>
)

sealed interface AroundListSideEffect {
    data class Toast(val message: String) : AroundListSideEffect
}