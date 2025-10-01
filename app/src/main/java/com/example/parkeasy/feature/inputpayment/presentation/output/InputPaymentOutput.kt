package com.example.parkeasy.feature.inputpayment.presentation.output

sealed interface InputPaymentOutput {
    object SavePaymentInfo : InputPaymentOutput
    object NavigateUp : InputPaymentOutput
}