package com.ecommerce.moneyswift.data.repository.repositoryImpl

import android.util.Log
import com.ecommerce.moneyswift.data.model.StripeResponse
import com.ecommerce.moneyswift.data.network.ApiService
import com.ecommerce.moneyswift.utils.ClientParser
import com.ecommerce.moneyswift.utils.CustomerParser
import com.ecommerce.moneyswift.utils.KeyParser
import com.ecommerce.moneyswift.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


const val error = "Error :"

class MoneySwiftRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val keyParser: KeyParser,
    private val clientParser: ClientParser,
    private val customerParser: CustomerParser
) /*:
    StripePaymentRepository */ {

    /*override*/ suspend fun createStripePayment(
        amount: String,
        currency: String
    ): Flow<ResultWrapper<StripeResponse>> {
        return flow {
            emit(ResultWrapper.Loading())
            try {
                val customerResponse = apiService.createStripePayment()
                //   val customer = customerParser.parse(response.body()!!)[0]

                if (customerResponse.isSuccessful && customerResponse.code() == 200) {
                    val keyResponse = withContext(Dispatchers.Default) {
                        getKey(customerResponse.body()?.id ?:"cus_QcqVOZKTpAii0e")
                    }
                    val clientResponse = withContext(Dispatchers.Default) {
                        getClient(customerResponse.body()?.id ?:"cus_QcqVOZKTpAii0e", amount, currency)
                    }

                    if (customerResponse.isSuccessful && customerResponse.body() != null
                        && keyResponse.isSuccessful && keyResponse.code() == 200
                        && clientResponse.isSuccessful && clientResponse.code() == 200
                    ) {
                        val customerID = customerResponse.body()?.id ?: ""
                        val key = keyResponse.body()?.secret ?: ""
                        val secretKey = clientResponse.body()?.client_secret ?: ""
                        Log.d("response", "$customerID,  $key,  $secretKey")
                        val stripeResponse =
                            StripeResponse(id = customerID, secret = key, client_secret = secretKey)
                        emit(
                            ResultWrapper.Success(
                                data = stripeResponse
                            )
                        )

                    } else {
                        emit(
                            ResultWrapper.Error(
                                error = "Error"
                            )
                        )
                    }
                } else {
                    emit(
                        ResultWrapper.Error(
                            error = "Error"
                        )
                    )
                }


            } catch (e: Exception) {
                emit(
                    ResultWrapper.Error(
                        error = "An error occurred ${e.localizedMessage}",
                        data = null,
                    ),
                )
            }


        }
    }


    /*override*/  suspend fun getKey(customerID: String) = apiService.getKey(customerID)


    /*override*/  suspend fun getClient(
        customerID: String,
        amount: String,
        currency: String
    ) = apiService.getClient(customerID, amount, currency)

}