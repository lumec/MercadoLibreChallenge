package com.lumec.challenge.data

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.lumec.challenge.domain.Error
import com.lumec.challenge.domain.ProductDetails
import com.lumec.challenge.domain.ProductPreview
import javax.inject.Inject

class ProductRepository @Inject constructor(private val remoteDataSource: ProductRemoteDataSource) {

    suspend fun getProductsByName(name: String): Either<Error, List<ProductPreview>> {
        return remoteDataSource.getProductsByName(name)
                .fold(
                    { error -> error.left() },
                    { products -> products.right() }
                )
    }

    suspend fun getProductDetailsById(productId: String): Either<Error, ProductDetails> {
        return remoteDataSource.getProductDetailsById(productId)
                .fold(
                    { error -> error.left() },
                    { productDetails -> productDetails.right() }
                )
    }
}