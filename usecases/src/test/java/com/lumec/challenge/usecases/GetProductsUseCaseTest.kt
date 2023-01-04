package com.lumec.challenge.usecases

import arrow.core.Either
import com.lumec.challenge.domain.Error
import com.lumec.challenge.domain.ProductPreview
import com.lumec.challenge.testshared.sampleProductList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetProductsUseCaseTest {

    @Test
    fun `Invoke calls product repository`(): Unit = runBlocking {
        val productList: Either<Error, List<ProductPreview>> = Either.Right(sampleProductList)
        val getProductsUseCase = GetProductsUseCase(mock {
            onBlocking { getProductsByName("product") } doReturn productList
        })

        val result = getProductsUseCase("product")

        assertEquals(productList, result)
    }

    @Test
    fun `Invoke calls product repository error`(): Unit = runBlocking {
        val error: Either<Error, List<ProductPreview>> = Either.Left(Error.Unknown)
        val getProductsUseCase = GetProductsUseCase(mock {
            onBlocking { getProductsByName("error") } doReturn error
        })

        val result = getProductsUseCase("error")

        assertEquals(error, result)
    }
}