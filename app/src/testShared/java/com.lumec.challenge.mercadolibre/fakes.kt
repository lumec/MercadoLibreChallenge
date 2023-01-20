package com.lumec.challenge.mercadolibre

import com.lumec.challenge.mercadolibre.framework.server.*

class FakeRemoteService(
    private val products: ProductListResponse = ProductListResponse(results = emptyList()),
    private val details: ProductDetailsResponse = sampleDetails,
    private val description: Description = Description(text = "")
): MercadoLibreApi {
    override suspend fun getProductsByName(productName: String): ProductListResponse = products

    override suspend fun getDetailsById(productId: String): ProductDetailsResponse = details

    override suspend fun getDescriptionById(productId: String): Description = description
}

val sampleDetails = ProductDetailsResponse(
    acceptsMercadopago = false,
    availableQuantity = 0,
    condition = "",
    shipping = Shipping(freeShipping = false),
    id = "",
    pictures = emptyList(),
    price = 0,
    secureThumbnail = "",
    title = ""
)
