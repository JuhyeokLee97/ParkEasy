package com.example.parkeasy.feature.inputcarinfo.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.parkeasy.feature.inputcarinfo.presentation.input.InputCarInfoViewModelInput
import com.example.parkeasy.feature.inputcarinfo.presentation.output.InputCarInfoViewModelOutput
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InputCarInfoViewModel @Inject constructor() : ViewModel(), InputCarInfoViewModelInput, InputCarInfoViewModelOutput {

    val output: InputCarInfoViewModelOutput = this
    val input: InputCarInfoViewModelInput = this

    private val _carNumberState: MutableState<String> = mutableStateOf("")
    override val carNumberState: State<String>
        get() = _carNumberState

    private val _carBrandState: MutableState<String> = mutableStateOf("")
    override val carBrandState: State<String>
        get() = _carBrandState

    private val _carModelState: MutableState<String> = mutableStateOf("")
    override val carModelState: State<String>
        get() = _carModelState

    private val _carColorState: MutableState<String> = mutableStateOf("")
    override val carColorState: State<String>
        get() = _carColorState

    override fun setCarNumber(carNumber: String) {
        _carNumberState.value = carNumber
    }

    override fun setCarBrand(carBrand: String) {
        _carBrandState.value = carBrand
    }

    override fun setCarModel(carModel: String) {
        _carModelState.value = carModel
    }

    override fun setCarColor(carColor: String) {
        _carColorState.value = carColor
    }

}