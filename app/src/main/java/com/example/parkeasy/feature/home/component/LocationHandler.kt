package com.example.parkeasy.feature.home.component

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng

@Composable
fun LocationHandler(
    isPermissionGranted: Boolean,
    onLocationReceived: (LatLng) -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(isPermissionGranted) {
        if (isPermissionGranted) {
            getCurrentLocation(context, onLocationReceived)
        }
    }
}

@SuppressLint("MissingPermission")
private fun getCurrentLocation(
    context: Context,
    onLocationReceived: (LatLng) -> Unit,
) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    fusedLocationClient.lastLocation
        .addOnSuccessListener { location ->
            location?.let {
                val currentLatLng = LatLng(it.latitude, it.longitude)
                onLocationReceived(currentLatLng)
            }
        }
        .addOnFailureListener {
            // todo Handle Error
        }
}