package com.example.presentation.main.aroundlist

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.domain.model.ParkingLot
import com.example.presentation.theme.ParkEasyTheme

@Composable
fun AroundListScreen(
    modifier: Modifier = Modifier,
    viewModel: AroundListViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.sideEffect.collect { effect ->
                when (effect) {
                    is AroundListSideEffect.Toast -> {
                        Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    AroundListScreen(
        parkingLots = uiState.parkingLots
    )
}

@Composable
private fun AroundListScreen(
    modifier: Modifier = Modifier,
    parkingLots: List<ParkingLot>
) {
    if (parkingLots.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "가까운 주차장이 없습니다.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    } else {
        LazyColumn {
            items(items = parkingLots, key = { it.id }) {
                ParkingLotCard(parkingLot = it)
            }
        }
    }
}

@Composable
private fun ParkingLotCard(
    parkingLot: ParkingLot,
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                parkingLot.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                "주소: ${parkingLot.address}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                "요금: 시간당 ${parkingLot.pricePerHour}원",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                "주차 가능 시간: ${parkingLot.availableStartTime} ~ ${parkingLot.availableEndTime}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
private fun AroundListScreenPreview() {
    ParkEasyTheme {
        AroundListScreen(
            parkingLots = emptyList()
        )
    }
}

@Preview
@Composable
private fun ParkingLotCardPreview() {
    ParkEasyTheme {
        ParkingLotCard(
            parkingLot = ParkingLot(
                id = 7,
                name = "삼성역 메트로시티 주차장",
                pricePerHour = 2200,
                address = "서울시 강남구 봉은사로 524",
                availableStartTime = "05:30",
                availableEndTime = "23:30",
                availablePlace = 34,
                imageUrl = "https://img.hankyung.com/photo/201806/01.16942523.1.jpg",
            )
        )
    }
}