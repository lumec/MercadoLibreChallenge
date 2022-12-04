package com.lumec.challenge.mercadolibre.ui.product_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.lumec.challenge.mercadolibre.usecases.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(ProductListUiState())
    val state: State<ProductListUiState> = _state

    private suspend fun getProducts(
        query: String
    ) {
        viewModelScope.launch {
            _state.value = ProductListUiState(isLoading = true)
            when(val result = getProductsUseCase(query)) {
                is Either.Left -> {
                    _state.value = ProductListUiState(error = result.value)
                }
                is Either.Right -> {
                    _state.value = ProductListUiState(products = result.value)
                }
            }
        }
    }

    fun onEvent(event: ProductListEvents) {
        when(event) {
            ProductListEvents.EraseIconClicked -> {
                _state.value = ProductListUiState(query = "")
            }
            is ProductListEvents.SearchQueryInput -> {
                viewModelScope.launch {
                    getProducts(event.query)
                }
            }
        }
    }
}