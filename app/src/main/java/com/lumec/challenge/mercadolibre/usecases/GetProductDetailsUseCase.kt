package com.lumec.challenge.mercadolibre.usecases

import arrow.core.Either
import com.lumec.challenge.mercadolibre.data.ProductRepository
import com.lumec.challenge.mercadolibre.domain.Error
import com.lumec.challenge.mercadolibre.domain.ProductDetails
import javax.inject.Inject

class GetProductDetailsUseCase @Inject constructor(
    private val productsRepository: ProductRepository
) {
    suspend operator fun invoke(productId: String): Either<Error, ProductDetails> =
        productsRepository.getProductDetailsById(productId)
}