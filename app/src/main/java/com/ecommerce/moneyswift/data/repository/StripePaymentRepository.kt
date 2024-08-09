package com.ecommerce.moneyswift.data.repository

import com.ecommerce.moneyswift.data.model.StripeResponse
import com.ecommerce.moneyswift.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface StripePaymentRepository {

    suspend fun createStripePayment(
        amount: String = "220",
        currency: String = "usd",
    ): Flow<ResultWrapper<StripeResponse>>

    suspend fun getKey(customerID: String): String

    suspend fun getClient(
        customerID: String,
        amount: String,
        currency: String,
    ): String
}
