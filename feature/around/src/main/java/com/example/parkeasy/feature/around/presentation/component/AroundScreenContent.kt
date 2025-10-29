package com.example.parkeasy.feature.around.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.parkeasy.core.ui.components.appbar.ParkEasyTopAppBar
import com.example.parkeasy.core.ui.model.icon.IconData
import com.example.parkeasy.feature.around.R
import com.example.parkeasy.feature.around.model.AroundUiState

@Composable
fun AroundScreenContent(
    uiState: AroundUiState,
    onNavigateToDetail: (id: Int) -> Unit,
) {
    Scaffold(
        topBar = {
            ParkEasyTopAppBar(
                title = "주변 주차장",
                navigationIconData = IconData.Resource(painter = painterResource(R.drawable.ic_back)),
                navigationIconContentDescription = "뒤로 가기",
                onNavigationClick = {}
            )
        }
    ) { innerPadding ->
        when (uiState) {
            is AroundUiState.Loading -> {
                AroundLoadingState(modifier = Modifier.padding(innerPadding))
            }

            is AroundUiState.Success -> {
                AroundSuccessState(
                    modifier = Modifier.padding(innerPadding),
                    uiState = uiState,
                    onNavigateToAroundParkingLot = { id ->
                        onNavigateToDetail(id)
                    }
                )
            }

            is AroundUiState.Error -> {}
        }
    }
}