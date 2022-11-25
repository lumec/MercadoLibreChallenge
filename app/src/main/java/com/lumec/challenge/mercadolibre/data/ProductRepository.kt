package com.lumec.challenge.mercadolibre.data

import com.lumec.challenge.mercadolibre.domain.Product
import javax.inject.Inject

class ProductRepository @Inject constructor(private val remoteDataSource: ProductRemoteDataSource) {

    suspend fun getProductsByName(name: String): List<Product> =
        remoteDataSource.getProductsByName(name)

}