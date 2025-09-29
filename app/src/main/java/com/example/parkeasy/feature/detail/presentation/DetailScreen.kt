package com.example.parkeasy.feature.detail.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parkeasy.R
import com.example.parkeasy.ui.component.CommonAppBar
import com.example.parkeasy.ui.component.OneButton
import com.example.parkeasy.ui.component.ParkEasyBottomBar
import com.example.parkeasy.ui.component.TopAppBar
import com.example.parkeasy.ui.theme.Paddings
import com.example.parkeasy.ui.theme.ParkEasyTheme
import com.example.parkeasy.ui.theme.color.ParkEasyLightColorScheme

val DETAIL_SCREEN = "DETAIL_SCREEN"
private val ThumbnailHeight = 150.dp

@Composable
fun DetailScreen(
    onBackClick: () -> Unit
) {
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
                ParkEasyBottomBar.OneButton(
                    modifier = Modifier.padding(Paddings.large),
                    text = stringResource(R.string.detail_reservation),
                    onClick = {},
                    color = MaterialTheme.colorScheme.primary
                )
            }
        ) { innerPadding ->
            BodyContent(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(ThumbnailHeight)
                .background(Color.Blue)
        )
        Spacer(modifier = Modifier.height(Paddings.large))
        Info()
        Spacer(modifier = Modifier.height(Paddings.large))
        AvailablePlaceCard()
    }
}

@Composable
fun ColumnScope.Info(modifier: Modifier = Modifier) {
    Column(
        modifier
            .padding(horizontal = Paddings.xLarge)
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)
    ) {
        Text(
            text = "강남역 지하주차장",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(Paddings.medium))
        InfoText("주소", "서울시 강남구 강남대로 123")
        Spacer(modifier = Modifier.height(Paddings.medium))
        InfoText("요금", "시간당 2,000원")
        Spacer(modifier = Modifier.height(Paddings.medium))
        InfoText("운영시간", "24시간 운영")
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
fun ColumnScope.AvailablePlaceCard() {
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
            text = "45",
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
    ParkEasyTheme {
        BodyContent()
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    ParkEasyTheme {
        DetailScreen({})
    }
}