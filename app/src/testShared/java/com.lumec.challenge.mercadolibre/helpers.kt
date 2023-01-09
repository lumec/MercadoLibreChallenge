package com.lumec.challenge.mercadolibre.ui

import com.lumec.challenge.data.ProductRepository
import com.lumec.challenge.mercadolibre.framework.server.*

fun buildRepositoryWith(
    products: ProductListResponse,
    details: ProductDetailsResponse,
    description: Description
): ProductRepository {
    val remoteDataSource =
        ProductServerDataSource(FakeRemoteService(products, details, description))
    return ProductRepository(remoteDataSource)
}

fun buildProductListResponse(productName: String) = (1..10).map {
    ProductPreviewResponse(
        acceptsMercadopago = true,
        id = "M00000$it",
        price = it * 1000,
        title = "$productName $it",
        thumbnail = ""
    )
}

fun buildProductDetailsResponse(productId: String) =
    ProductDetailsResponse(
        acceptsMercadopago = true,
        availableQuantity = 10,
        condition = "new",
        shipping = Shipping(freeShipping = false),
        id = productId,
        pictures = emptyList(),
        price = 10000,
        secureThumbnail = "",
        title = "Product $productId"
    )

fun buildDescriptionResponse(productId: String) =
    Description(
        text = "Description of product $productId"
    )
