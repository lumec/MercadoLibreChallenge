package com.lumec.challenge.usecases

import arrow.core.Either
import com.lumec.challenge.domain.Error
import com.lumec.challenge.domain.ProductDetails
import com.lumec.challenge.testshared.sampleProductDetails
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetProductDetailsUseCaseTest {

    @Test
    fun `Invoke calls product repository`(): Unit = runBlocking {
        val productDetails: Either<Error, ProductDetails> =
            Either.Right(sampleProductDetails.copy(id = "M0000003"))
        val getProductDetailsUseCase = GetProductDetailsUseCase(mock {
            onBlocking { getProductDetailsById("id") } doReturn productDetails
        })

        val result = getProductDetailsUseCase("id")

        Assert.assertEquals(productDetails, result)
    }
}