package com.lumec.challenge.usecases

import arrow.core.Either
import com.lumec.challenge.data.ProductRepository
import com.lumec.challenge.domain.Error
import com.lumec.challenge.domain.ProductPreview

import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productsRepository: ProductRepository
) {
    suspend operator fun invoke(name: String): Either<Error, List<ProductPreview>> =
        productsRepository.getProductsByName(name)
}