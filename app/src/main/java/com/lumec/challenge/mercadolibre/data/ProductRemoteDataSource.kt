package com.lumec.challenge.mercadolibre.data

import com.lumec.challenge.mercadolibre.domain.Product

interface ProductRemoteDataSource {

    suspend fun getProductsByName(name: String): List<Product>

}