package com.lumec.challenge.usecases

import arrow.core.Either
import com.lumec.challenge.data.ProductRepository
import com.lumec.challenge.domain.Error
import com.lumec.challenge.domain.ProductDetails
import javax.inject.Inject

class GetProductDetailsUseCase @Inject constructor(
    private val productsRepository: ProductRepository
) {
    suspend operator fun invoke(productId: String): Either<Error, ProductDetails> =
        productsRepository.getProductDetailsById(productId)
}