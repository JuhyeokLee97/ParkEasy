package com.example.parkeasy.feature.arround

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.parkeasy.R
import com.example.parkeasy.feature.arround.data.ParkingLotEntity
import com.example.parkeasy.feature.arround.data.ParkingLotState
import com.example.parkeasy.ui.theme.Paddings
import com.example.parkeasy.ui.theme.ParkEasyTheme

val PARK_EASY_SCREEN = "PARK_EASY_SCREEN"

@Composable
fun AroundParkScreen(
    viewModel: AroundParkViewModel = hiltViewModel()
) {
    val viewModelOutput = viewModel.output
    viewModel.init()
    Scaffold(
        topBar = { AroundParkAppBar() },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BodyContent(
                parkingLotsState = viewModelOutput.parkingLotsState.collectAsState().value,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AroundParkAppBar() = TopAppBar(
    title = {
        Text(
            text = "주변 주차장 찾기",
            style = MaterialTheme.typography.titleLarge
        )
    },
    navigationIcon = {
        IconButton(onClick = {}) {
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back"
            )
        }
    }
)

@Composable
fun BodyContent(
    modifier: Modifier,
    parkingLotsState: ParkingLotState
) {
    when (parkingLotsState) {
        is ParkingLotState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        is ParkingLotState.Loaded -> {
            LazyColumn {
                items(parkingLotsState.parkingLots, key = { it.id }) {
                    ParkItem(it)
                }
            }
        }
    }
}

@Composable
fun ParkItem(park: ParkingLotEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = Paddings.small,
                horizontal = Paddings.xLarge
            )
    ) {
        Column(
            modifier = Modifier.padding(horizontal = Paddings.large)
        ) {
            Text(
                text = park.name,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(Paddings.small))
            Text(
                text = "주소: ${park.address}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(Paddings.small))
            Text(
                text = "요금: 시간당 ${park.pricePerHour}원",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(Paddings.small))
            Text(
                text = "${park.availableTime}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(Paddings.small))
            Text(
                text = "${park.availablePlace} 이용 가능",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                )
            )
        }
    }
}

@Preview
@Composable
fun ParkItemPreview() {
    ParkEasyTheme {
        ParkItem(
            park = ParkingLotEntity(
                id = 1,
                name = "강남역 주차장",
                pricePerHour = 1000,
                address = "서울시 강남구 강남대로 123",
                availableTime = "주차 가능 시간: 06:00 ~ 23:00",
                availablePlace = 10
            )
        )
    }
}