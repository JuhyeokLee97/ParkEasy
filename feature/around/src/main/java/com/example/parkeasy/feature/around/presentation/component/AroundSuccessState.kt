package com.example.parkeasy.feature.around.presentation.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.parkeasy.feature.around.model.AroundUiState

@Composable
fun AroundSuccessState(
    modifier: Modifier = Modifier,
    uiState: AroundUiState.Success,
    onNavigateToAroundParkingLot: (id: Int) -> Unit,
) {

    LazyColumn(modifier = modifier) {
        items(items = uiState.parkingLots, key = { it.id }) {
            ParkingLotItem(
                parkingLot = it,
                onNavigateToDetail = onNavigateToAroundParkingLot
            )
        }
    }
}