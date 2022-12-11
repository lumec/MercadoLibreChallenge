package com.lumec.challenge.mercadolibre.ui.product_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.lumec.challenge.usecases.GetProductDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(ProductDetailsUiState())
    val state: State<ProductDetailsUiState> = _state

    init {
        savedStateHandle.get<String>("productId")?.let{ productId ->
            getProductDetails(productId)
        }
    }

    private fun getProductDetails(productId: String) {
        _state.value = ProductDetailsUiState(isLoading = true)
        viewModelScope.launch {
            when(val product = getProductDetailsUseCase(productId)) {
                is Either.Left -> {_state.value = ProductDetailsUiState(error = product.value)}
                is Either.Right -> { _state.value = ProductDetailsUiState(product = product.value)}
            }
        }
    }
}