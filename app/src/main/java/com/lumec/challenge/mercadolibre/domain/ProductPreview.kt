package com.lumec.challenge.mercadolibre.domain

data class ProductPreview(
    val acceptsMercadopago: Boolean,
    val availableQuantity: Int,
    val id: String,
    val pictureUrl: String,
    val price: Int,
    val title: String,
)
