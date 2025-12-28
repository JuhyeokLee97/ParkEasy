package com.example.presentation.main.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var hasLocationPermission by remember {
        mutableStateOf(checkLocationPermission(context))
    }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasLocationPermission = isGranted

        if (isGranted) {
            viewModel.loadCurrentLocation()
        } else {
            viewModel.useDefaultLocation()
        }
    }

    LaunchedEffect(hasLocationPermission) {
        if (hasLocationPermission) {
            viewModel.loadCurrentLocation()
        } else {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            viewModel.useDefaultLocation()
        }
    }

    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            hasLocationPermission = checkLocationPermission(context)
        }
    }

    LaunchedEffect(lifecycleOwner) {
        viewModel.sideEffect.collect {
            when (it) {
                is HomeSideEffect.Toast -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    HomeScreen(
        isMyLocationEnabled = hasLocationPermission,
        homeState = uiState
    )
}

private fun checkLocationPermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}

@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    isMyLocationEnabled: Boolean = false,
    homeState: HomeUiState,
) {

    Surface {
        ParkingLotMap(
            modifier = Modifier.fillMaxSize(),
            isMyLocationEnabled = isMyLocationEnabled,
            currentLocation = LatLng(
                homeState.currentLocation.latitude,
                homeState.currentLocation.longitude
            )
        )
    }
}

@Composable
private fun ParkingLotMap(
    modifier: Modifier = Modifier,
    isMyLocationEnabled: Boolean,
    currentLocation: LatLng,
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currentLocation, 15f)
    }

    LaunchedEffect(currentLocation) {
        cameraPositionState.animate(
            update = CameraUpdateFactory.newLatLngZoom(currentLocation, 15f),
            durationMs = 500
        )
    }

    Box(modifier) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                isMyLocationEnabled = isMyLocationEnabled
            ),
            uiSettings = MapUiSettings(
                myLocationButtonEnabled = isMyLocationEnabled,
                zoomControlsEnabled = true,
                zoomGesturesEnabled = true
            )
        )
    }
}