package com.example.parkeasy.feature.inputpayment.presentation.output

data class InputPaymentUiState(
    val cardType: InputPaymentCardType = InputPaymentCardType.CREDIT,
    val cardNumber: String = "",
    val cardExpiryDate: String = "",
    val cardCvc: String = "",
    val cardCompany: String = ""
)