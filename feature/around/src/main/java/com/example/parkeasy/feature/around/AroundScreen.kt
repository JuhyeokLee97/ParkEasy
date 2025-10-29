package com.example.parkeasy.feature.around

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.parkeasy.feature.around.presentation.AroundViewModel
import com.example.parkeasy.feature.around.presentation.component.AroundScreenContent

@Composable
fun AroundScreen(
    viewModel: AroundViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchParkingLots()
    }

    AroundScreenContent(
        uiState = uiState,
        onNavigateToDetail = {}
    )
}