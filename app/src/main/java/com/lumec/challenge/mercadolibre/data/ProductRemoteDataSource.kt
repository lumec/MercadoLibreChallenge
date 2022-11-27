package com.lumec.challenge.mercadolibre.data

import com.lumec.challenge.mercadolibre.domain.ProductDetails
import com.lumec.challenge.mercadolibre.domain.ProductPreview

interface ProductRemoteDataSource {

    suspend fun getProductsByName(name: String): List<ProductPreview>

    suspend fun getProductDetailsById(productId: String): ProductDetails

}