package com.lumec.challenge.mercadolibre.ui.product_detail

import com.lumec.challenge.domain.Error
import com.lumec.challenge.domain.ProductDetails

data class ProductDetailsUiState(
    val isLoading: Boolean = false,
    val product: ProductDetails? = null,
    val error: Error? = null,
)