package com.example.parkeasy.feature.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.parkeasy.R
import com.example.parkeasy.feature.home.component.HomeContent
import com.example.parkeasy.feature.home.component.LocationHandler
import com.example.parkeasy.feature.home.component.PermissionHandler
import com.example.parkeasy.feature.home.component.rememberLocationPermissionState
import com.example.parkeasy.feature.home.data.HomeInput
import com.example.parkeasy.feature.home.presentation.HomeViewModel
import com.example.parkeasy.ui.component.CommonAppBar
import com.example.parkeasy.ui.component.ServicePreparingDialog
import com.example.parkeasy.ui.component.TopAppBarWithAction
import com.example.parkeasy.ui.theme.ParkEasyTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi

val HOME_SCREEN = "HOME_SCREEN"

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToParkEasy: () -> Unit = {},
    onNavigateToMyPage: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val locationPermissionsState = rememberLocationPermissionState()

    PermissionHandler(
        permissionState = locationPermissionsState,
        onPermissionGranted = {
            viewModel.handleInput(HomeInput.LocationPermissionGranted)
        }
    )

    LocationHandler(
        isPermissionGranted = locationPermissionsState.allPermissionsGranted,
        onLocationReceived = { location ->
            viewModel.handleInput(HomeInput.UpdatedLocation(location))
        }
    )

    ServicePreparingDialog(
        visible = uiState.showServicePreparingDialog,
        onDismiss = { viewModel.handleInput(HomeInput.DismissDialog) }
    )

    Scaffold(
        topBar = {
            CommonAppBar.TopAppBarWithAction(
                title = stringResource(id = R.string.app_name),
                onActionClick = onNavigateToMyPage,
                actionResId = R.drawable.img_profile,
                contentDescription = "Profile Image"
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        HomeContent(
            currentLocation = uiState.currentLocation,
            isLocationLoading = uiState.isLocationLoading,
            nearbyParkingLots = uiState.nearbyParkingLots,
            onNavigateToParkEasy = onNavigateToParkEasy,
            onFavoriteClick = {
                viewModel.handleInput(HomeInput.FavoriteClicked)
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    ParkEasyTheme {
        HomeScreen()
    }
}