package com.ecommerce.moneyswift.data.network

import com.ecommerce.moneyswift.data.model.StripeResponse
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("/v1/customers")
    suspend fun createStripePayment(
    ): Response<StripeResponse>

    @POST("/v1/ephemeral_keys")
    @Headers("Stripe-Version: 2024-06-20")
    suspend fun getKey(
        @Query("customer") customerID: String,
       // @Query("Stripe-Version") version: String = "2024-06-20"
    ): Response<StripeResponse>

    @POST("/v1/payment_intents")
    suspend fun getClient(
        @Query("customer") customerID: String,
        @Query("amount") amount: String,
        @Query("currency") currency: String,
    ): Response<StripeResponse>
}