package com.ecommerce.moneyswift

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.ecommerce.moneyswift.data.model.Product




import com.ecommerce.moneyswift.presentation.screens.CheckoutScreen
import com.ecommerce.moneyswift.presentation.state.UiState
import org.junit.Rule
import org.junit.Test

class CheckoutScreenTest {
    @get:Rule
    val rule = createComposeRule()
    val samples = listOf(
        Product("Product 1", "100", R.drawable.product),
        Product("Product 2", "200", R.drawable.product),
        Product("Product 3", "300", R.drawable.product),
        Product("Product 4", "400", R.drawable.product),
        Product("Product 5", "500", R.drawable.product)
    )
    @Test
    fun test_pay_now_is_clickable_when_loading_false() {
        rule.setContent {
            CheckoutScreen(
                state = UiState(
                    isLoading = false,
                ),
                onEvent = {},
                navigateBack = {},
                product = Product("product 1","100", R.drawable.product),

            )
        }
        rule.onNodeWithTag("pay_button").assertHasClickAction()
    }

}