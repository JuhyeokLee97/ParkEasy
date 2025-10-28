package com.example.parkeasy.feature.home.model

data class DialogState(
    val servicePreparingDialogState: ServicePreparingDialogState = ServicePreparingDialogState.Hidden
)

sealed interface ServicePreparingDialogState{
    object Visible: ServicePreparingDialogState
    object Hidden: ServicePreparingDialogState
}