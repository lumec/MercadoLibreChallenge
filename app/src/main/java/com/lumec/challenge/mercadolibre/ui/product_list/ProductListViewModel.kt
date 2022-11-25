package com.lumec.challenge.mercadolibre.ui.product_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lumec.challenge.mercadolibre.common.Resource
import com.lumec.challenge.mercadolibre.domain.Product
import com.lumec.challenge.mercadolibre.usecases.RequestProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val requestProductsUseCase: RequestProductsUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(ProductListUiState())
    val state: State<ProductListUiState> = _state

    private val product = "xxxxxxxxxxxxxxxxx"

    init {
        getProducts()
    }

    private fun getProducts() {
        requestProductsUseCase(product).onEach { result ->
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