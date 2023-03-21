package com.lumec.challenge.domain

data class ProductDetails(
    val acceptsMercadopago: Boolean,
    val availableQuantity: Int,
    val condition: String,
    val description: String,
    val freeShipping: Boolean,
    val id: String,
    val location: String,
    val pictureUrl: String,
    val picturesUrl: List<String>,
    val price: Int,
    val title: String
)
