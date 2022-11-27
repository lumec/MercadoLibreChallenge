package com.lumec.challenge.mercadolibre.framework.server

import com.google.gson.annotations.SerializedName

data class Shipping(
    @SerializedName("free_shipping")
    val freeShipping: Boolean?,
)