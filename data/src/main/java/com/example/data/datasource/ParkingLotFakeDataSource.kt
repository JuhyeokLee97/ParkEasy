package com.example.data.datasource

import com.example.data.model.ParkingLotDto
import javax.inject.Inject
import kotlin.random.Random

class ParkingLotFakeDataSource @Inject constructor(): ParkingLotDataSource {

    override suspend fun getParkingLots(latitude: Double, longitude: Double): List<ParkingLotDto> {
        val random = Random(42)

        return listOf(
            ParkingLotDto(
                id = 1,
                name = "강남역 지하주차장",
                pricePerHour = 2000,
                address = "서울시 강남구 강남대로 396",
                availableStartTime = "00:00",
                availableEndTime = "24:00",
                availablePlace = 45,
                imageUrl = "https://cdn.sisaweek.com/news/photo/202101/140644_133244_4240.jpg",
                latitude = latitude + random.nextDouble(0.0, 0.05),
                longitude = longitude + random.nextDouble(0.0, 0.05)
            ),
            ParkingLotDto(
                id = 2,
                name = "코엑스 주차장",
                pricePerHour = 2500,
                address = "서울시 강남구 영동대로 513",
                availableStartTime = "06:00",
                availableEndTime = "22:00",
                availablePlace = 23,
                imageUrl = "https://img.segye.com/content/image/2018/08/02/20180802552368.jpg",
                latitude = latitude + random.nextDouble(0.0, 0.05),
                longitude = longitude + random.nextDouble(0.0, 0.05)
            ),
            ParkingLotDto(
                id = 3,
                name = "선릉역 주차타워",
                pricePerHour = 1800,
                address = "서울시 강남구 테헤란로 415",
                availableStartTime = "00:00",
                availableEndTime = "24:00",
                availablePlace = 78,
                imageUrl = "https://cdn.iusm.co.kr/news/photo/202303/1010340_559368_555.jpg",
                latitude = latitude + random.nextDouble(0.0, 0.05),
                longitude = longitude + random.nextDouble(0.0, 0.05)
            ),
            ParkingLotDto(
                id = 4,
                name = "롯데월드몰 주차장",
                pricePerHour = 3000,
                address = "서울시 송파구 올림픽로 300",
                availableStartTime = "06:00",
                availableEndTime = "24:00",
                availablePlace = 12,
                imageUrl = "https://cdn.crowdpic.net/detail-thumb/thumb_d_0DC64B74FCF4C343EA9AEDF80621B90F.jpg",
                latitude = latitude + random.nextDouble(0.0, 0.05),
                longitude = longitude + random.nextDouble(0.0, 0.05)
            ),
            ParkingLotDto(
                id = 5,
                name = "신논현역 공영주차장",
                pricePerHour = 1500,
                address = "서울시 강남구 논현로 842",
                availableStartTime = "07:00",
                availableEndTime = "22:00",
                availablePlace = 67,
                imageUrl = "https://img.khan.co.kr/news/2022/04/12/2022041301001416000129921.jpg",
                latitude = latitude - random.nextDouble(0.0, 0.05),
                longitude = longitude - random.nextDouble(0.0, 0.05)
            ),
            ParkingLotDto(
                id = 6,
                name = "압구정로데오 주차장",
                pricePerHour = 3500,
                address = "서울시 강남구 압구정로 317",
                availableStartTime = "08:00",
                availableEndTime = "20:00",
                availablePlace = 5,
                imageUrl = "https://img.danawa.com/cp_images/service/99/4282699/bf05b8c2.jpg",
                latitude = latitude - random.nextDouble(0.0, 0.05),
                longitude = longitude - random.nextDouble(0.0, 0.05)
            ),
            ParkingLotDto(
                id = 7,
                name = "삼성역 메트로시티 주차장",
                pricePerHour = 2200,
                address = "서울시 강남구 봉은사로 524",
                availableStartTime = "05:30",
                availableEndTime = "23:30",
                availablePlace = 34,
                imageUrl = "https://img.hankyung.com/photo/201806/01.16942523.1.jpg",
                latitude = latitude - random.nextDouble(0.0, 0.05),
                longitude = longitude - random.nextDouble(0.0, 0.05)
            ),
            ParkingLotDto(
                id = 8,
                name = "한강공원 반포 주차장",
                pricePerHour = 1200,
                address = "서울시 서초구 신반포로 343",
                availableStartTime = "06:00",
                availableEndTime = "21:00",
                availablePlace = 89,
                imageUrl = "https://cdn.crowdpic.net/detail-thumb/thumb_d_760427244AE7509772468259E7B953E9.jpg",
                latitude = latitude - random.nextDouble(0.0, 0.05),
                longitude = longitude - random.nextDouble(0.0, 0.05)
            ),
            ParkingLotDto(
                id = 9,
                name = "교보타워 지하주차장",
                pricePerHour = 4000,
                address = "서울시 강남구 테헤란로 152",
                availableStartTime = "07:00",
                availableEndTime = "19:00",
                availablePlace = 3,
                imageUrl = "https://pimg.mk.co.kr/news/cms/202408/13/news-p.v1.20240813.ccf95826664342eb8886452c021c67b7_P1.jpg",
                latitude = latitude - random.nextDouble(0.0, 0.05),
                longitude = longitude - random.nextDouble(0.0, 0.05)
            ),
            ParkingLotDto(
                id = 10,
                name = "가로수길 공용주차장",
                pricePerHour = 2800,
                address = "서울시 강남구 가로수길 29",
                availableStartTime = "09:00",
                availableEndTime = "22:00",
                availablePlace = 52,
                imageUrl = "https://www.pmnews.co.kr/imgdata/pmnews_co_kr/202204/2022042857181225.jpg",
                latitude = latitude - random.nextDouble(0.0, 0.05),
                longitude = longitude - random.nextDouble(0.0, 0.05)
            )
        )
    }
}