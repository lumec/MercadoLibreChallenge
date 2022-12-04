package com.lumec.challenge.mercadolibre.usecases

import arrow.core.Either
import com.lumec.challenge.mercadolibre.data.ProductRepository
import com.lumec.challenge.mercadolibre.domain.Error
import com.lumec.challenge.mercadolibre.domain.ProductPreview
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productsRepository: ProductRepository
) {
    suspend operator fun invoke(name: String): Either<Error, List<ProductPreview>> =
        productsRepository.getProductsByName(name)
}