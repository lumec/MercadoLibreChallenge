package com.lumec.challenge.mercadolibre.ui.product_list

import com.lumec.challenge.mercadolibre.domain.Product

data class ProductListUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String = "",
)