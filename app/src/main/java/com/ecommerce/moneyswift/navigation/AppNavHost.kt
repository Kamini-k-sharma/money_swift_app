package com.ecommerce.moneyswift.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ecommerce.moneyswift.R
import com.ecommerce.moneyswift.data.model.Product
import com.ecommerce.moneyswift.presentation.screens.CheckoutScreen
import com.ecommerce.moneyswift.presentation.screens.CheckoutScreenRoute
import com.ecommerce.moneyswift.presentation.screens.ConfirmationScreen
import com.ecommerce.moneyswift.presentation.screens.ProductListScreen
import com.ecommerce.moneyswift.presentation.state.UiState
import com.stripe.android.paymentsheet.PaymentSheet

@Composable
fun AppNavHost(
    navController: NavHostController,
    paymentSheet: PaymentSheet,
    startDestination: String = Route.PRODUCT_SCREEN
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Route.PRODUCT_SCREEN) {
            val products = listOf(
                Product("Product 1", "100", R.drawable.product),
                Product("Product 2", "200", R.drawable.product),
                Product("Product 3", "300", R.drawable.product),
                Product("Product 4", "400", R.drawable.product),
                Product("Product 5", "500", R.drawable.product)
            )
            ProductListScreen(UiState(productList = products), onProductSelected = { product ->
                navController.navigate("checkout/${product.name}/${product.price}/${product.image}")
            })
        }
        composable(Route.CHECKOUT_SCREEN) { backStackEntry ->
            val productName = backStackEntry.arguments?.getString("productName") ?: ""
            val productPrice = backStackEntry.arguments?.getString("productPrice") ?: "0"
            val productImage =
                R.drawable.product
            val product = Product(
                productName, productPrice, productImage
            )
            CheckoutScreenRoute(
                product = product,
                onPaymentSuccess = {
                    navController.navigate(Route.SUCCESS_SCREEN)
                },
                paymentSheet = paymentSheet,
                navigateBack = {
                    navController.navigateUp()
                },
            )
        }
        composable(Route.SUCCESS_SCREEN) {
            ConfirmationScreen(
                toBack = {
                    navController.navigate(Route.PRODUCT_SCREEN) {
                        popUpTo(Route.CHECKOUT_SCREEN) { inclusive = true }
                        popUpTo(Route.CHECKOUT_SCREEN) { inclusive = true }
                    }
                },
            )
        }
    }
}

