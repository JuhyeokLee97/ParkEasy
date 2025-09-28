package com.example.parkeasy.repository.data

data class ParkingLot(
    val id: Int,
    val name: String,
    val pricePerHour: Int,
    val address: String,
    val availableStartTime: String, // 07:00
    val availableEndTime: String, // 23:00
    val availablePlace: Int
)

val mockParkingLots = listOf(
    ParkingLot(
        id = 1,
        name = "강남역 지하주차장",
        pricePerHour = 2000,
        address = "서울시 강남구 강남대로 396",
        availableStartTime = "00:00",
        availableEndTime = "24:00",
        availablePlace = 45
    ),
    ParkingLot(
        id = 2,
        name = "코엑스 주차장",
        pricePerHour = 2500,
        address = "서울시 강남구 영동대로 513",
        availableStartTime = "06:00",
        availableEndTime = "22:00",
        availablePlace = 23
    ),
    ParkingLot(
        id = 3,
        name = "선릉역 주차타워",
        pricePerHour = 1800,
        address = "서울시 강남구 테헤란로 415",
        availableStartTime = "00:00",
        availableEndTime = "24:00",
        availablePlace = 78
    ),
    ParkingLot(
        id = 4,
        name = "롯데월드몰 주차장",
        pricePerHour = 3000,
        address = "서울시 송파구 올림픽로 300",
        availableStartTime = "06:00",
        availableEndTime = "24:00",
        availablePlace = 12
    ),
    ParkingLot(
        id = 5,
        name = "신논현역 공영주차장",
        pricePerHour = 1500,
        address = "서울시 강남구 논현로 842",
        availableStartTime = "07:00",
        availableEndTime = "22:00",
        availablePlace = 67
    ),
    ParkingLot(
        id = 6,
        name = "압구정로데오 주차장",
        pricePerHour = 3500,
        address = "서울시 강남구 압구정로 317",
        availableStartTime = "08:00",
        availableEndTime = "20:00",
        availablePlace = 5
    ),
    ParkingLot(
        id = 7,
        name = "삼성역 메트로시티 주차장",
        pricePerHour = 2200,
        address = "서울시 강남구 봉은사로 524",
        availableStartTime = "05:30",
        availableEndTime = "23:30",
        availablePlace = 34
    ),
    ParkingLot(
        id = 8,
        name = "한강공원 반포 주차장",
        pricePerHour = 1200,
        address = "서울시 서초구 신반포로 343",
        availableStartTime = "06:00",
        availableEndTime = "21:00",
        availablePlace = 89
    ),
    ParkingLot(
        id = 9,
        name = "교보타워 지하주차장",
        pricePerHour = 4000,
        address = "서울시 강남구 테헤란로 152",
        availableStartTime = "07:00",
        availableEndTime = "19:00",
        availablePlace = 3
    ),
    ParkingLot(
        id = 10,
        name = "가로수길 공용주차장",
        pricePerHour = 2800,
        address = "서울시 강남구 가로수길 29",
        availableStartTime = "09:00",
        availableEndTime = "22:00",
        availablePlace = 52
    )
)