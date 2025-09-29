package com.example.parkeasy.feature.inputcarinfo.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.parkeasy.R
import com.example.parkeasy.ui.component.CommonAppBar
import com.example.parkeasy.ui.component.ParkEasyBottomBar
import com.example.parkeasy.ui.component.RowTowButtons
import com.example.parkeasy.ui.component.TopAppBar
import com.example.parkeasy.ui.theme.Paddings
import com.example.parkeasy.ui.theme.ParkEasyTheme

val INPUT_CAR_INFO_SCREEN = "INPUT_CAR_INFO_SCREEN"

@Composable
fun InputCarInfoScreen(
    onBackClick: () -> Unit = {},
    viewModel: InputCarInfoViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CommonAppBar.TopAppBar(
                title = stringResource(R.string.input_car_info_title),
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            ParkEasyBottomBar.RowTowButtons(
                modifier = Modifier
                    .padding(
                        horizontal = Paddings.large,
                        vertical = Paddings.medium
                    ),
                firstButtonText = stringResource(R.string.cancel),
                firstButtonOnClick = onBackClick,
                secondButtonText = stringResource(R.string.save),
                secondButtonOnClick = onBackClick
            )
        }
    ) { innerPadding ->
        BodyContent(
            modifier = Modifier.padding(innerPadding),
            viewModel = viewModel
        )

    }
}

@Composable
fun BodyContent(
    modifier: Modifier = Modifier,
    viewModel: InputCarInfoViewModel
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Paddings.large),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.my_car_info_title),
            modifier = Modifier
                .padding(
                    vertical = Paddings.medium,
                    horizontal = Paddings.large
                )
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(Paddings.large))
        InfoItem(
            key = stringResource(R.string.car_number),
            value = viewModel.output.carNumberState.value,
            onValueChange = viewModel.input::setCarNumber
        )

        Spacer(modifier = Modifier.height(Paddings.large))
        InfoItem(
            key = stringResource(R.string.car_brand),
            value = viewModel.output.carBrandState.value,
            onValueChange = viewModel.input::setCarBrand
        )

        Spacer(modifier = Modifier.height(Paddings.large))
        InfoItem(
            key = stringResource(R.string.car_model),
            value = viewModel.output.carModelState.value,
            onValueChange = viewModel.input::setCarModel
        )

        Spacer(modifier = Modifier.height(Paddings.large))
        InfoItem(
            key = stringResource(R.string.car_color),
            value = viewModel.output.carColorState.value,
            onValueChange = viewModel.input::setCarColor
        )
    }
}

@Composable
fun InfoItem(
    key: String,
    value: String,
    onValueChange: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = key,
            style = MaterialTheme.typography.titleMedium,
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(top = Paddings.small)
                .fillMaxWidth(),
            value = value,
            onValueChange = onValueChange
        )
    }
}


@Preview
@Composable
fun InputCarInfoScreenPreview() {
    ParkEasyTheme {
        InputCarInfoScreen()
    }
}