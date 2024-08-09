package com.ecommerce.moneyswift.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.moneyswift.data.repository.repositoryImpl.MoneySwiftRepositoryImpl
import com.ecommerce.moneyswift.presentation.state.UiState
import com.ecommerce.moneyswift.utils.ResultWrapper
import com.flexcode.ecommerce.presentation.event.HomeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoneySwiftViewModel @Inject constructor(private val repository: MoneySwiftRepositoryImpl) :
    ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    /**
     * init' s the payment with stripe
     * @param amount
     * @param currency
     */
    fun initStripeBilling(
        amount: String = "220",
        currency: String = "usd",
    ) {
        viewModelScope.launch {

            val response = repository.createStripePayment(amount, currency)
                .collectLatest { response ->
                    _state.update {
                        when (response) {
                            is ResultWrapper.Success -> {
                                it.copy(
                                    isLoading = false,
                                    stripeResponse = response.data,
                                )
                            }

                            is ResultWrapper.Loading -> {
                                it.copy(isLoading = true)
                            }

                            is ResultWrapper.Error -> {
                                it.copy(
                                    isLoading = false,
                                    errorMsg = "An error ${response.errorMessage}",
                                )
                            }
                        }
                    }
                }


        }

    }

    fun onEvent(event: HomeEvent) {
        when (event) {

            is HomeEvent.ResetState -> {
                resetState()
            }

            is HomeEvent.StripeBilling -> {
                initStripeBilling(event.amount, event.currency)
            }

            HomeEvent.ResetStripeState -> {
                _state.update { it.copy(stripeSuccessPayment = null) }
            }
        }
    }

    fun resetState() {
        _state.update {
            it.copy(
                isLoading = false,
                errorMsg = null,
                successMsg = null,
                stripeResponse = null,
            )
        }
    }

    fun setStripSuccess() {
        _state.update { it.copy(stripeSuccessPayment = "Payment made successfully") }
    }
}



