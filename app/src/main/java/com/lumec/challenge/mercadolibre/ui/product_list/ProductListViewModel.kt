package com.lumec.challenge.mercadolibre.ui.product_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lumec.challenge.mercadolibre.common.Resource
import com.lumec.challenge.mercadolibre.usecases.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(ProductListUiState())
    val state: State<ProductListUiState> = _state

    private val product = "samsung s7"

    init {
        getProducts()
    }

    private fun getProducts() {
        getProductsUseCase(product).onEach { result ->
            Log.e("log", "result-> ${result.data}")

            when(result) {
                is Resource.Success -> {
                    _state.value = ProductListUiState(products = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ProductListUiState(error = result.message ?:
                    "An expected error occurred!!")
                }
                is Resource.Loading -> {
                    _state.value = ProductListUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}