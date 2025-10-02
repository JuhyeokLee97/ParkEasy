package com.example.parkeasy.feature.home.data

import com.google.android.gms.maps.model.LatLng

sealed interface HomeInput {
    object FavoriteClicked : HomeInput
    object DismissDialog : HomeInput
    data class UpdatedLocation(val location: LatLng): HomeInput
    object LocationPermissionGranted : HomeInput
}