package com.example.parkeasy.feature.around.data

import com.google.android.gms.maps.model.LatLng

data class ParkingLotEntity(
    val id: Int,
    val name: String,
    val pricePerHour: Int,
    val address: String,
    val availableTime: String,
    val availablePlace: Int,
    val latitude: Double,
    val longitude: Double,
) {
    fun toLatLng() = LatLng(latitude, longitude)
}
