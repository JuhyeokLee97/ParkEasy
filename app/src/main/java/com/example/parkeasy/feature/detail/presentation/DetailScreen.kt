package com.example.parkeasy.feature.detail.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.parkeasy.R
import com.example.parkeasy.feature.detail.data.DetailInput
import com.example.parkeasy.feature.detail.data.ParkingLotDetailEntity
import com.example.parkeasy.feature.detail.data.ParkingLotState
import com.example.parkeasy.ui.component.CommonAppBar
import com.example.parkeasy.ui.component.OneButton
import com.example.parkeasy.ui.component.ParkEasyBottomBar
import com.example.parkeasy.ui.component.ServicePreparingDialog
import com.example.parkeasy.ui.component.TopAppBar
import com.example.parkeasy.ui.theme.Paddings
import com.example.parkeasy.ui.theme.ParkEasyTheme
import com.example.parkeasy.ui.theme.color.ParkEasyLightColorScheme

val DETAIL_SCREEN = "DETAIL_SCREEN"
private val ThumbnailHeight = 150.dp

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ServicePreparingDialog(
        visible = uiState.showServicePreparingDialog,
        onDismiss = { viewModel.handleInput(DetailInput.DismissDialog) }
    )

    ParkEasyTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CommonAppBar.TopAppBar(
                    title = stringResource(R.string.detail_title),
                    onBackClick = onBackClick
                )
            },
            bottomBar = {
                if (uiState.parkingLotState is ParkingLotState.Success) {
                    ParkEasyBottomBar.OneButton(
                        modifier = Modifier.padding(Paddings.large),
                        text = stringResource(R.string.detail_reservation),
                        onClick = { viewModel.handleInput(DetailInput.ReservationClicked) },
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        ) { innerPadding ->
            when (val state = uiState.parkingLotState) {
                is ParkingLotState.Loading -> {
                    LoadingContent(modifier = Modifier.padding(innerPadding))
                }

                is ParkingLotState.Success -> {
                    BodyContent(
                        parkingLot = state.parkingLot,
                        modifier = Modifier.padding(innerPadding)
                    )
                }

                is ParkingLotState.Error -> {
                    ErrorContent(
                        message = state.message,
                        onRetry = { viewModel.retry() },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LoadingContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorContent(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(Paddings.large),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(Paddings.large))
        Button(onClick = onRetry) {
            Text(text = "다시 시도")
        }
    }
}

@Composable
fun BodyContent(
    parkingLot: ParkingLotDetailEntity,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(ThumbnailHeight)
                .background(Color.Blue)
        )
        Spacer(modifier = Modifier.height(Paddings.large))
        Info(parkingLot = parkingLot)
        Spacer(modifier = Modifier.height(Paddings.large))
        AvailablePlaceCard(availablePlace = parkingLot.availablePlace)
    }
}

@Composable
fun ColumnScope.Info(
    parkingLot: ParkingLotDetailEntity,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .padding(horizontal = Paddings.xLarge)
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)
    ) {
        Text(
            text = parkingLot.name,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(Paddings.medium))
        InfoText("주소", parkingLot.address)
        Spacer(modifier = Modifier.height(Paddings.medium))
        InfoText("요금", "시간당 ${parkingLot.pricePerHour}원")
        Spacer(modifier = Modifier.height(Paddings.medium))
        InfoText("운영시간", parkingLot.availableTime)
        Spacer(modifier = Modifier.height(Paddings.medium))
        InfoText("총 주차면", "100면")
    }
}

@Composable
fun InfoText(key: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = key,
            style = MaterialTheme.typography.titleSmall
        )

        Spacer(modifier = Modifier.height(Paddings.medium))

        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun ColumnScope.AvailablePlaceCard(
    availablePlace: Int
) {
    Card(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .fillMaxWidth()
            .background(ParkEasyLightColorScheme.surface)
            .padding(horizontal = Paddings.xLarge)
            .clip(MaterialTheme.shapes.large),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = Paddings.large),
            text = availablePlace.toString(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayLarge
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = Paddings.medium),
            text = stringResource(R.string.detail_available_place_desc),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview
@Composable
fun BodyContentPreview() {
    val previewParkingLot = ParkingLotDetailEntity(
        id = 1,
        name = "강남역 지하주차장",
        pricePerHour = 2000,
        address = "서울시 강남구 강남대로 396",
        availableTime = "00:00 ~ 24:00",
        availablePlace = 45
    )
    ParkEasyTheme {
        BodyContent(
            parkingLot = previewParkingLot
        )
    }
}