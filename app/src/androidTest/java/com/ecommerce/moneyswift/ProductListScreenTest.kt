package com.ecommerce.moneyswift

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.ecommerce.moneyswift.presentation.screens.ProductListScreen
import com.ecommerce.moneyswift.presentation.state.UiState
import org.junit.Rule
import org.junit.Test

class ProductListScreenTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun test_error_state_shows_error_text() {
        rule.setContent {
            ProductListScreen(
                state = UiState(
                    productList = listOf(),
                    errorMsg = "Error",
                ),
                onProductSelected = {}

            )
        }
        rule.onNodeWithTag("ecommerce_error_text").assertIsDisplayed()
    }

    @Test
    fun test_emplty_list_shows_no_result() {
        rule.setContent {
            ProductListScreen(
                state = UiState(
                    productList = emptyList(),

                    ),
                onProductSelected = {}

            )
        }
        rule.onNodeWithTag("ecommerce_error_indicator").assertIsDisplayed()
    }
}