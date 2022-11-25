package com.lumec.challenge.mercadolibre.domain


data class Product(
    val id: String,
    val title: String,
    val price: Int,
    val soldQuantity: Int,
    val condition: String,
    val pictureUrl: String,
    val availableQuantity: Int,
    val acceptsMercadopago: Boolean
)
