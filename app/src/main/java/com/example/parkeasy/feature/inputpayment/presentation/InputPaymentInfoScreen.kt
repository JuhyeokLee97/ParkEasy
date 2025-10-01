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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.parkeasy.R
import com.example.parkeasy.feature.inputpayment.presentation.input.InputPaymentInput
import com.example.parkeasy.feature.inputpayment.presentation.output.InputPaymentCardType
import com.example.parkeasy.feature.inputpayment.presentation.output.InputPaymentOutput
import com.example.parkeasy.feature.inputpayment.presentation.output.InputPaymentUiState
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
    viewModel: InputPaymentInfoViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(Unit) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.RESUMED) {
            viewModel.sideEffect.collect { effect ->
                when (effect) {
                    is InputPaymentOutput.SavePaymentInfo -> onBackClick()
                    is InputPaymentOutput.NavigateUp -> onBackClick()
                }
            }
        }
    }

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
                firstButtonOnClick = {
                    viewModel.handleInput(InputPaymentInput.CancelClicked)
                },
                secondButtonText = stringResource(R.string.save),
                secondButtonOnClick = {
                    viewModel.handleInput(InputPaymentInput.SaveClicked)
                },
            )
        }
    ) { innerPadding ->
        BodyContent(
            uiState = uiState,
            onInput = viewModel::handleInput,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyContent(
    uiState: InputPaymentUiState,
    onInput: (InputPaymentInput) -> Unit,
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
        CardType(
            selectedCardType = uiState.cardType,
            onCardTypeSelected = { cardType ->
                onInput(InputPaymentInput.UpdateCardType(cardType))
            }
        )
        Spacer(modifier = Modifier.height(Paddings.large))
        CardNumber(
            value = uiState.cardNumber,
            onValueChange = { number ->
                onInput(InputPaymentInput.UpdateCardNumber(number))
            }
        )

        Spacer(modifier = Modifier.height(Paddings.large))
        Row(
            modifier = Modifier
                .padding(top = Paddings.none)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Paddings.small)
        ) {
            CardExpiryDate(
                value = uiState.cardExpiryDate,
                onValueChange = { date ->
                    onInput(InputPaymentInput.UpdatedCardExpiryDate(date))
                },
                modifier = Modifier.weight(1f)
            )
            CardCvc(
                value = uiState.cardCvc,
                onValueChange = { cvc ->
                    onInput(InputPaymentInput.UpdateCardCvc(cvc))
                },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(Paddings.large))
        CardCompany(
            value = uiState.cardCompany,
            onValueChange = { company ->
                onInput(InputPaymentInput.UpdateCardCompany(company))
            }
        )
    }
}

@Composable
fun ColumnScope.CardType(
    selectedCardType: InputPaymentCardType,
    onCardTypeSelected: (InputPaymentCardType) -> Unit
) {
    Text(
        text = stringResource(R.string.card_type),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleMedium
    )

    Spacer(modifier = Modifier.height(Paddings.large))
    CardTypeOption(
        text = stringResource(R.string.credit_card),
        selected = selectedCardType == InputPaymentCardType.CREDIT,
        onClick = {
            onCardTypeSelected(InputPaymentCardType.CREDIT)
        }
    )

    Spacer(modifier = Modifier.height(Paddings.large))
    CardTypeOption(
        text = stringResource(R.string.check_card),
        selected = selectedCardType == InputPaymentCardType.CHECK,
        onClick = {
            onCardTypeSelected(InputPaymentCardType.CHECK)
        }
    )
}

@Composable
fun CardNumber(
    value: String,
    onValueChange: (String) -> Unit
) {
    CardInfoItem(
        key = stringResource(R.string.card_number),
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = stringResource(R.string.card_number_placeholder)) }
    )
}

@Composable
fun CardExpiryDate(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    CardInfoItem(
        modifier = modifier,
        key = stringResource(R.string.card_expiry_date),
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = stringResource(R.string.card_expiry_date_placeholder)) }
    )
}

@Composable
fun CardCvc(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    CardInfoItem(
        modifier = modifier,
        key = stringResource(R.string.card_cvc),
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = stringResource(R.string.card_cvc_placeholder)) }
    )
}

@Composable
fun CardCompany(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    CardInfoItem(
        modifier = modifier.fillMaxWidth(),
        key = stringResource(R.string.card_company),
        value = value,
        onValueChange = onValueChange,
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