package com.ecommerce.moneyswift.presentation.state

import com.ecommerce.moneyswift.data.model.Product
import com.ecommerce.moneyswift.data.model.StripeResponse

data class UiState(
    val isLoading: Boolean = false,
    val errorMsg: String? = null,
    val successMsg: String? = null,
    val stripeResponse: StripeResponse? = null,
    val stripeSuccessPayment: String? = null,
    val productList: List<Product> = emptyList(),
)
