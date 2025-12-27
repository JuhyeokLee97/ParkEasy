package com.example.presentation.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Location
import com.example.domain.usecase.main.home.GetCurrentLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        getCurrentLocation()
    }

    private fun getCurrentLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            getCurrentLocationUseCase().fold(
                onSuccess = { location ->
                    _uiState.value = HomeUiState.Success(currentLocation = location)
                },
                onFailure = {
                    _uiState.value = HomeUiState.Error
                    // todo side effect toast
                }
            )
        }
    }
}

interface HomeUiState {
    object Loading : HomeUiState
    data class Success(
        val currentLocation: Location,
    ) : HomeUiState

    object Error : HomeUiState
}