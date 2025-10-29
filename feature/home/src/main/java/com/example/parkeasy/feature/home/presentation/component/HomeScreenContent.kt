package com.example.parkeasy.feature.home.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.parkeasy.core.ui.components.appbar.ParkEasyTopAppBar
import com.example.parkeasy.core.ui.model.icon.IconData
import com.example.parkeasy.feature.home.R
import com.example.parkeasy.feature.home.model.HomeUIState

@Composable
internal fun HomeScreenContent(
    uiState: HomeUIState,
    onNavigateToAroundParkingLot: () -> Unit,
    onShowServicePreparingDialog: () -> Unit,
    onHideDialogs: () -> Unit
) {
    Scaffold(
        topBar = {
            ParkEasyTopAppBar(
                title = "ParkEasy",
                actionIconData = IconData.Resource(painter = painterResource(R.drawable.img_profile)),
                actionIconContentDescription = "프로필 이미지",
                onActionClick = {}
            )
        },
        modifier = Modifier.fillMaxWidth()
    ) { innerPadding ->
        when (uiState) {
            is HomeUIState.Loading -> {
                HomeLoadingState(modifier = Modifier.padding(innerPadding))
            }

            is HomeUIState.Success -> {
                HomeSuccessState(
                    uiState = uiState,
                    modifier = Modifier.padding(innerPadding),
                    onNavigateToAroundParkingLot = onNavigateToAroundParkingLot,
                    onFavoriteClick = onShowServicePreparingDialog,
                )
            }
            is HomeUIState.Error -> {}
        }
    }
}