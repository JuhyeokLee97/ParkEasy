package com.example.parkeasy.feature.detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parkeasy.feature.detail.data.DetailUiState
import com.example.parkeasy.feature.detail.domain.usecase.GetParkingLotUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getParkingLotUseCase: GetParkingLotUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val parkingLotId: Int = savedStateHandle[PARKING_LOT_ID] ?: -1

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    init {
        loadParkingLot()
    }

    private fun loadParkingLot() {
        viewModelScope.launch {
            _uiState.value = DetailUiState.Loading

            try {
                val parkingLot = getParkingLotUseCase(parkingLotId)

                if (parkingLot != null) {
                    _uiState.value = DetailUiState.Success(parkingLot)
                } else {
                    _uiState.value = DetailUiState.Error("주차장을 찾을 수 없습니다.")
                }
            } catch (e: Exception) {
                _uiState.value = DetailUiState.Error(e.message ?: "알 수 없는 오류가 발생했습니다.")
            }
        }
    }

    fun retry() {
        loadParkingLot()
    }

    companion object {
        const val PARKING_LOT_ID = "PARKING_LOT_ID"
    }
}