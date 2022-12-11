package com.lumec.challenge.data

import arrow.core.Either
import com.lumec.challenge.domain.Error
import com.lumec.challenge.domain.ProductDetails
import com.lumec.challenge.domain.ProductPreview

interface ProductRemoteDataSource {

    suspend fun getProductsByName(name: String): Either<Error, List<ProductPreview>>

    suspend fun getProductDetailsById(productId: String): Either<Error, ProductDetails>

}