package com.example.parkeasy.feature.home.component

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun rememberLocationPermissionState(): MultiplePermissionsState = rememberMultiplePermissionsState(
    permissions = listOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
)

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionHandler(
    permissionState: MultiplePermissionsState,
    onPermissionGranted: () -> Unit
) {
    LaunchedEffect(permissionState.allPermissionsGranted) {
        if (permissionState.allPermissionsGranted) {
            onPermissionGranted()
        }
    }
}