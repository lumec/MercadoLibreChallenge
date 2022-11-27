package com.lumec.challenge.mercadolibre.data

import com.lumec.challenge.mercadolibre.domain.ProductDetails
import com.lumec.challenge.mercadolibre.domain.ProductPreview
import javax.inject.Inject

class ProductRepository @Inject constructor(private val remoteDataSource: ProductRemoteDataSource) {

    suspend fun getProductsByName(name: String): List<ProductPreview> =
        remoteDataSource.getProductsByName(name)

    suspend fun getProductDetailsById(productId: String): ProductDetails =
        remoteDataSource.getProductDetailsById(productId)
}