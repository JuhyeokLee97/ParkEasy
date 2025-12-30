package com.example.domain.model

data class Location(
    val latitude: Double,
    val longitude: Double,
) {
    companion object {
        // 기본 위치 (예: 서울시청)
        val DEFAULT = Location(
            latitude = 37.5665,
            longitude = 126.9780
        )
    }
}