package com.lumec.challenge.mercadolibre.framework.server


import com.google.gson.annotations.SerializedName

data class Description(
    @SerializedName("plain_text")
    val text: String?
)