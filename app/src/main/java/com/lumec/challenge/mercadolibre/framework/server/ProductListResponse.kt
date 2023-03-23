package com.lumec.challenge.mercadolibre.framework.server

import com.google.gson.annotations.SerializedName

data class ProductListResponse(
    val results: List<ProductPreviewResponse>
)

data class ProductPreviewResponse(
    @SerializedName("accepts_mercadopago")
    val acceptsMercadopago: Boolean?,
    val id: String?,
    @SerializedName("seller_address")
    val sellerAddress: SellerAddress?,
    val price: Int?,
    val title: String?,
    val thumbnail: String?,
)
