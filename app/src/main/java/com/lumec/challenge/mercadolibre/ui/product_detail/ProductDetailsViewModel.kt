package com.lumec.challenge.mercadolibre.ui.product_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lumec.challenge.mercadolibre.common.Resource
import com.lumec.challenge.mercadolibre.usecases.GetProductDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(ProductDetailsUiState())
    val state: State<ProductDetailsUiState> = _state

    private val productId = "MCO948360573"

    init {
        getProductDetails()
    }

    private fun getProductDetails() {
        getProductDetailsUseCase(productId).onEach { result ->
            Log.e("log", "result details-> ${result.data}")

            when(result) {
                is Resource.Success -> {
                    _state.value = ProductDetailsUiState(product = result.data)
                }
                is Resource.Error -> {
                    _state.value = ProductDetailsUiState(error = result.message ?:
                    "An expected error occurred!!")
                }
                is Resource.Loading -> {
                    _state.value = ProductDetailsUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}