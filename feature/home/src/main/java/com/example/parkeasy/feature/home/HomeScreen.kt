package com.example.parkeasy.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.parkeasy.feature.home.presentation.HomeViewModel
import com.example.parkeasy.feature.home.presentation.component.HomeScreenContent

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToAroundParkingLot: () -> Unit,
    onNavigateToMyPage: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadNearbyParkingLots()
    }

    HomeScreenContent(
        uiState = uiState,
        onNavigateToAroundParkingLot = onNavigateToAroundParkingLot,
        onNavigateToMyPage = onNavigateToMyPage,
        onShowServicePreparingDialog = viewModel::showServicePreparingDialog,
        onDismissDialog = viewModel::dismissDialog,
    )
}