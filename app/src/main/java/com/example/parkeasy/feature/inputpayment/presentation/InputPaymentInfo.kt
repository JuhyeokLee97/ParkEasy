package com.example.parkeasy.feature.inputpayment.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
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
import com.example.parkeasy.R
import com.example.parkeasy.ui.component.CardTypeOption
import com.example.parkeasy.ui.component.CommonAppBar
import com.example.parkeasy.ui.component.ParkEasyBottomBar
import com.example.parkeasy.ui.component.RowTowButtons
import com.example.parkeasy.ui.component.TopAppBar
import com.example.parkeasy.ui.theme.Paddings
import com.example.parkeasy.ui.theme.ParkEasyTheme

val INPUT_PAYMENT_INFO_SCREEN = "INPUT_PAYMENT_INFO_SCREEN"

@Composable
fun InputPaymentInfoScreen(
    onBackClick: () -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CommonAppBar.TopAppBar(
                title = stringResource(R.string.input_payment_info_title),
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
                secondButtonOnClick = onBackClick,
            )
        }
    ) { innerPadding ->
        BodyContent(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = Paddings.large),
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
        CardType()
    }
}

@Composable
fun ColumnScope.CardType(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.card_type),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleMedium
    )

    Spacer(modifier = Modifier.height(Paddings.large))
    CardTypeOption(
        text = stringResource(R.string.credit_card),
        selected = true,
        onClick = { }
    )

    Spacer(modifier = Modifier.height(Paddings.large))
    CardTypeOption(
        text = stringResource(R.string.check_card),
        selected = false,
        onClick = { }
    )

    Spacer(modifier = Modifier.height(Paddings.large))
    CardNumber()

    Spacer(modifier = Modifier.height(Paddings.large))
    Row(
        modifier = Modifier
            .padding(top = Paddings.none)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Paddings.small)
    ) {
        CardExpiryDate(modifier = modifier.weight(1f))
        CardCvc(modifier = modifier.weight(1f))
    }

    Spacer(modifier = Modifier.height(Paddings.large))
    CardCompany()
}

@Composable
fun CardNumber() {
    CardInfoItem(
        key = stringResource(R.string.card_number),
        value = "",
        onValueChange = {},
        placeholder = { Text(text = stringResource(R.string.card_number_placeholder)) }
    )
}

@Composable
fun CardExpiryDate(modifier: Modifier = Modifier) {
    CardInfoItem(
        modifier = modifier,
        key = stringResource(R.string.card_expiry_date),
        value = "",
        placeholder = { Text(text = stringResource(R.string.card_expiry_date_placeholder)) }
    )
}

@Composable
fun CardCvc(modifier: Modifier = Modifier) {
    CardInfoItem(
        modifier = modifier,
        key = stringResource(R.string.card_cvc),
        value = "",
        placeholder = { Text(text = stringResource(R.string.card_cvc_placeholder)) }
    )
}

@Composable
fun CardCompany(modifier: Modifier = Modifier) {
    CardInfoItem(
        modifier = modifier.fillMaxWidth(),
        key = stringResource(R.string.card_company),
        value = "",
        placeholder = { Text(text = stringResource(R.string.card_company_placeholder)) }
    )
}

@Composable
fun CardInfoItem(
    modifier: Modifier = Modifier,
    key: String,
    value: String,
    onValueChange: (String) -> Unit = {},
    placeholder: @Composable () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxWidth(),
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
            onValueChange = onValueChange,
            placeholder = placeholder
        )
    }
}

@Preview
@Composable
fun InputPaymentInfoScreenPreview() {
    ParkEasyTheme {
        InputPaymentInfoScreen()
    }
}