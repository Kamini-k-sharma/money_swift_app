package com.ecommerce.moneyswift

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ecommerce.moneyswift.navigation.AppNavHost
import com.ecommerce.moneyswift.navigation.Route
import com.ecommerce.moneyswift.ui.theme.MoneySwiftTheme
import com.ecommerce.moneyswift.viewModels.MoneySwiftViewModel
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewmodel: MoneySwiftViewModel by viewModels()
    private lateinit var navController: NavHostController

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val paymentSheet = PaymentSheet(this) { paymentSheetResult ->
            onPaymentSheetResult(paymentSheetResult, navController)
        }
        installSplashScreen()
        setContent {
            navController = rememberNavController()
            MoneySwiftTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        // .navigationBarsPadding()
                        .imePadding(),

                    bottomBar = {
                    },

                    ) { innerPadding ->
                    Log.d("content padding", "$innerPadding")
                    AppNavHost(navController = navController, paymentSheet = paymentSheet)

                }


            }
        }
    }

    private fun onPaymentSheetResult(
        paymentSheetResult: PaymentSheetResult,
        navController: NavHostController
    ) {
        when (paymentSheetResult) {
            is PaymentSheetResult.Canceled -> {
                Toast.makeText(
                    this,
                    "Stripe Payment cancelled",
                    Toast.LENGTH_SHORT,
                ).show()
                viewmodel.resetState()
            }

            is PaymentSheetResult.Failed -> {
                Toast.makeText(
                    this,
                    "Error: ${paymentSheetResult.error.message}",
                    Toast.LENGTH_SHORT,
                )
                    .show()
                viewmodel.resetState()
            }

            is PaymentSheetResult.Completed -> {
                Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show()

                navController.navigate(Route.SUCCESS_SCREEN)
                viewmodel.setStripSuccess()
                viewmodel.resetState()
            }
        }
    }

}



