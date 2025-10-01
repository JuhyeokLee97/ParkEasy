package com.example.parkeasy.feature.home.data

sealed interface HomeInput {
    object FavoriteClicked : HomeInput
    object DismissDialog : HomeInput
}