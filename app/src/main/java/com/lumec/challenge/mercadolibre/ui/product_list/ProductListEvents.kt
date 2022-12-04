package com.lumec.challenge.mercadolibre.ui.product_list

sealed class ProductListEvents{
    object EraseIconClicked: ProductListEvents()
    data class SearchQueryInput(val query: String): ProductListEvents()
}
