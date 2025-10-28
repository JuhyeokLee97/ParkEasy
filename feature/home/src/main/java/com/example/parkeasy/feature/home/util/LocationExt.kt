package com.example.parkeasy.feature.home.util

import com.example.parkeasy.core.domain.model.Location
import com.google.android.gms.maps.model.LatLng

fun Location.toLatLng(): LatLng = LatLng(latitude, longitude)