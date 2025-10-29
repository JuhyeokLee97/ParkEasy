package com.example.parkeasy.feature.around.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.example.parkeasy.core.domain.model.ParkingLot
import com.example.parkeasy.core.ui.util.Paddings

@Composable
fun ParkingLotItem(
    parkingLot: ParkingLot,
    onNavigateToDetail: (id: Int) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = Paddings.small,
                horizontal = Paddings.xLarge
            ),
        onClick = { onNavigateToDetail(parkingLot.id) }
    ) {
        Column(
            modifier = Modifier.padding(horizontal = Paddings.large)
        ) {
            ParkingLotTitle(title = parkingLot.name)
            Spacer(modifier = Modifier.height(Paddings.small))
            ParkingLotInfoRow(
                label = "주소",
                value = parkingLot.address,
            )
            Spacer(modifier = Modifier.height(Paddings.small))
            ParkingLotInfoRow(
                label = "요금",
                value = "시간당 ${parkingLot.pricePerHour}",
            )
            Spacer(modifier = Modifier.height(Paddings.small))
            ParkingLotOperatingHours(
                startTime = parkingLot.availableStartTime,
                endTime = parkingLot.availableEndTime
            )
            Spacer(modifier = Modifier.height(Paddings.small))
            ParkingLotAvailability(availablePlace = parkingLot.availablePlace)
        }
    }
}

@Composable
private fun ParkingLotTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier
    )
}

@Composable
private fun ParkingLotInfoRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "$label: $value",
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
    )
}

@Composable
private fun ParkingLotOperatingHours(
    startTime: String,
    endTime: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "주차 가능 시간: $startTime ~ $endTime",
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
    )
}

@Composable
private fun ParkingLotAvailability(
    availablePlace: Int,
    modifier: Modifier = Modifier
) {
    val displayText = when {
        availablePlace == 0 -> "만차"
        availablePlace < 5 -> "거의 만차 (${availablePlace}대)"
        else -> "${availablePlace}대 이용 가능"
    }
    Text(
        text = displayText,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
    )
}