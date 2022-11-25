package com.lumec.challenge.mercadolibre.framework.server


import com.google.gson.annotations.SerializedName

data class RemoteResponse(
    val results: List<RemoteProduct>
)

data class RemoteProduct(

    @SerializedName("accepts_mercadopago")
    val acceptsMercadopago: Boolean?,
    @SerializedName("available_quantity")
    val availableQuantity: Int?,
    val condition: String?,
    val id: String?,
    val price: Int?,
    val title: String?,
    @SerializedName("sold_quantity")
    val soldQuantity: Int?,
    @SerializedName("thumbnail_id")
    val thumbnailId: String?,
)