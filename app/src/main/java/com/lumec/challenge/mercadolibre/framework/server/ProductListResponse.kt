package com.lumec.challenge.mercadolibre.framework.server

import com.google.gson.annotations.SerializedName

data class ProductListResponse(
    val results: List<ProductPreviewResponse>
)

data class ProductPreviewResponse(
    @SerializedName("accepts_mercadopago")
    val acceptsMercadopago: Boolean?,
    @SerializedName("available_quantity")
    val availableQuantity: Int?,
    val id: String?,
    val price: Int?,
    val title: String?,
    val thumbnail: String?,
)
