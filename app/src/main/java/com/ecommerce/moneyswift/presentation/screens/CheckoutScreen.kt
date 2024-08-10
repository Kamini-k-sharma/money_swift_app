package com.ecommerce.moneyswift.presentation.screens

// CheckoutScreen.kt

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ecommerce.moneyswift.R
import com.ecommerce.moneyswift.data.model.Product
import com.ecommerce.moneyswift.data.model.StripeResponse
import com.ecommerce.moneyswift.presentation.NavigationBar
import com.ecommerce.moneyswift.presentation.state.UiState
import com.ecommerce.moneyswift.ui.theme.buttonColor
import com.ecommerce.moneyswift.utils.Constants
import com.ecommerce.moneyswift.viewModels.MoneySwiftViewModel
import com.flexcode.ecommerce.presentation.event.HomeEvent
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet

@Composable
fun CheckoutScreenRoute(
    viewModel: MoneySwiftViewModel = hiltViewModel(),
    product: Product,
    onPaymentSuccess: () -> Unit,
    paymentSheet: PaymentSheet,
    navigateBack: () -> Unit,
) {
    val onEvent = viewModel::onEvent
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current.applicationContext
    LaunchedEffect(key1 = state.stripeSuccessPayment) {
        state.stripeSuccessPayment?.let {
            // to success
            onPaymentSuccess()
            onEvent(HomeEvent.ResetStripeState)
            onEvent(HomeEvent.ResetState)
        }
    }

    LaunchedEffect(key1 = state.errorMsg) {
        state.errorMsg?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }
    LaunchedEffect(state.stripeResponse) {
        state.stripeResponse?.let {
            PaymentConfiguration.init(
                context,
                Constants.PUBLISH_KEY,
            )
            paymentFlow(paymentSheet = paymentSheet, it)
        }
    }
    CheckoutScreen(
        product = product,
        navigateBack = navigateBack,
        onEvent = onEvent,
        state = state
    )
}

@Composable
fun CheckoutScreen(
    product: Product,
    navigateBack: () -> Unit,
    onEvent: (HomeEvent) -> Unit,
    state: UiState = UiState(),
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            NavigationBar(onBack = navigateBack, label = "Checkout")
            Image(
                painter = painterResource(id = R.drawable.product),
                contentDescription = product.name
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = product.name, fontSize = 20.sp)
                Text(text = "Price - $${product.price}", fontSize = 20.sp, color = Color.Gray)
            }
            Button(modifier = Modifier
                .fillMaxWidth()
                .testTag("pay_button")
                .padding(32.dp),
                enabled = !state.isLoading,
                onClick = {
                    onEvent(
                        HomeEvent.StripeBilling(
                            "100",
                            "usd",
                        ),
                    )
                }) {
                Text(text = "Pay now", color = buttonColor, modifier = Modifier.padding(8.dp))

            }
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                color = Color.Blue,
                trackColor = Color.White,
                modifier = Modifier
                    .size(48.dp, 48.dp)
                    .align(
                        Alignment.Center,
                    ),

                )
        }
    }
}

fun paymentFlow(
    paymentSheet: PaymentSheet,
    response: StripeResponse
) {

    response?.let {
        paymentSheet.presentWithPaymentIntent(
            it.client_secret,
            PaymentSheet.Configuration(
                "Kamini Sharma",
                PaymentSheet.CustomerConfiguration(
                    it.id,
                    it.secret,
                ),
            ),
        )

    }
}

