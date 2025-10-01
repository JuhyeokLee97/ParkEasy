package com.example.parkeasy.feature.inputpayment.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parkeasy.feature.inputpayment.presentation.input.InputPaymentInput
import com.example.parkeasy.feature.inputpayment.presentation.output.InputPaymentCardType
import com.example.parkeasy.feature.inputpayment.presentation.output.InputPaymentOutput
import com.example.parkeasy.feature.inputpayment.presentation.output.InputPaymentUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputPaymentInfoViewModel @Inject constructor() : ViewModel() {

    private val _uiState: MutableStateFlow<InputPaymentUiState> = MutableStateFlow(InputPaymentUiState())
    val uiState: StateFlow<InputPaymentUiState> = _uiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<InputPaymentOutput> = MutableSharedFlow(replay = 0)
    val sideEffect: SharedFlow<InputPaymentOutput> = _sideEffect.asSharedFlow()

    fun handleInput(input: InputPaymentInput) {
        when (input) {
            is InputPaymentInput.UpdateCardType -> updateCardType(input.cardType)
            is InputPaymentInput.UpdateCardNumber -> updateCardNumber(input.number)
            is InputPaymentInput.UpdatedCardExpiryDate -> updateCardExpiryDate(input.date)
            is InputPaymentInput.UpdateCardCvc -> updateCardCvc(input.cvc)
            is InputPaymentInput.UpdateCardCompany -> updateCardCompany(input.company)
            is InputPaymentInput.SaveClicked -> saveCardInfo()
            is InputPaymentInput.CancelClicked -> navigateUp()
        }
    }

    private fun updateCardType(cardType: InputPaymentCardType) {
        _uiState.value = _uiState.value.copy(cardType = cardType)
    }

    private fun updateCardNumber(number: String) {
        _uiState.value = _uiState.value.copy(cardNumber = number)
    }

    private fun updateCardExpiryDate(date: String) {
        _uiState.value = _uiState.value.copy(cardExpiryDate = date)
    }

    private fun updateCardCvc(cvc: String) {
        _uiState.value = _uiState.value.copy(cardCvc = cvc)
    }

    private fun updateCardCompany(company: String) {
        _uiState.value = _uiState.value.copy(cardCompany = company)
    }

    private fun saveCardInfo() {
        viewModelScope.launch {
            _sideEffect.emit(InputPaymentOutput.SavePaymentInfo)
        }
    }

    private fun navigateUp() {
        viewModelScope.launch {
            _sideEffect.emit(InputPaymentOutput.NavigateUp)
        }
    }
}



