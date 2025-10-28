package com.example.parkeasy.feature.home.util

import com.example.parkeasy.core.domain.model.ParkingLot
import com.google.android.gms.maps.model.LatLng

fun ParkingLot.toLatLng(): LatLng = LatLng(latitude, longitude)

fun ParkingLot.toAvailableTime(): String = "$availableStartTime ~ $availableEndTime"

fun ParkingLot.formatterPrice(): String = "${pricePerHour}원/시간"