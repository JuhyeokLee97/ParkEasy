package com.example.parkeasy.feature.detail.data

sealed interface DetailInput {
    object ReservationClicked: DetailInput
    object DismissDialog: DetailInput
}