package com.example.parkeasy.feature.mypage.presentation.input

// 사용자 액션 (UI -> ViewModel)
sealed class MyPageInput {

    object NavigateToCarInfo: MyPageInput()
    object NavigateToPaymentInfo: MyPageInput()
    object NavigateToReservationHistory: MyPageInput()
    object NavigateToSetting: MyPageInput()
    object NavigateUp: MyPageInput()
    object Logout: MyPageInput()
}