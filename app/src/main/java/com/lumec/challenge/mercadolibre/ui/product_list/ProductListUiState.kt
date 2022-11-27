package com.lumec.challenge.mercadolibre.ui.product_list

import com.lumec.challenge.mercadolibre.domain.ProductPreview

data class ProductListUiState(
    val isLoading: Boolean = false,
    val products: List<ProductPreview> = emptyList(),
    val error: String = "",
)