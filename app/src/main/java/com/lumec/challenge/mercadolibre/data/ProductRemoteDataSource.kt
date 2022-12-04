package com.lumec.challenge.mercadolibre.data

import arrow.core.Either
import com.lumec.challenge.mercadolibre.domain.Error
import com.lumec.challenge.mercadolibre.domain.ProductDetails
import com.lumec.challenge.mercadolibre.domain.ProductPreview

interface ProductRemoteDataSource {

    suspend fun getProductsByName(name: String): Either<Error, List<ProductPreview>>

    suspend fun getProductDetailsById(productId: String): Either<Error, ProductDetails>

}