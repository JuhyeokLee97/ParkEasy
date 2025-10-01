package com.example.parkeasy.feature.inputpayment.presentation.input

import com.example.parkeasy.feature.inputpayment.presentation.output.InputPaymentCardType

sealed interface InputPaymentInput {
    data class UpdateCardType(val cardType: InputPaymentCardType) : InputPaymentInput
    data class UpdateCardNumber(val number: String) : InputPaymentInput
    data class UpdatedCardExpiryDate(val date: String) : InputPaymentInput
    data class UpdateCardCvc(val cvc: String) : InputPaymentInput
    data class UpdateCardCompany(val company: String) : InputPaymentInput
    object SaveClicked : InputPaymentInput
    object CancelClicked : InputPaymentInput
}