package com.lumec.challenge.mercadolibre.domain

data class ProductPreview(
    val acceptsMercadopago: Boolean,
    val id: String,
    val pictureUrl: String,
    val price: Int,
    val title: String,
)
