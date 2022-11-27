package com.lumec.challenge.mercadolibre.framework.server


import com.google.gson.annotations.SerializedName

data class Picture(
    @SerializedName("secure_url")
    val secureUrl: String?,
)