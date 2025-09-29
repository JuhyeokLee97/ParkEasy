package com.example.parkeasy.feature.inputcarinfo.presentation.output

import androidx.compose.runtime.State

interface InputCarInfoViewModelOutput {
    val carNumberState: State<String>
    val carBrandState: State<String>
    val carModelState: State<String>
    val carColorState: State<String>
}