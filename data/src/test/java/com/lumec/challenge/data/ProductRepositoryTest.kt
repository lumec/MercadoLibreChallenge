package com.lumec.challenge.data

import arrow.core.Either
import arrow.core.right
import com.lumec.challenge.domain.Error
import com.lumec.challenge.domain.ProductDetails
import com.lumec.challenge.domain.ProductPreview
import com.lumec.challenge.testshared.sampleProductDetails
import com.lumec.challenge.testshared.sampleProductList
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class ProductRepositoryTest {

    @Mock
    lateinit var remoteDataSource: ProductRemoteDataSource

    private lateinit var productsRepository: ProductRepository

    @Before
    fun setUp() {
        productsRepository = ProductRepository(remoteDataSource)
    }

    @Test
    fun `when list of products by name success, return product list from server`() = runBlocking {
        //Given
        val expectedResult: Either<Error, List<ProductPreview>> = Either.Right(sampleProductList)
        whenever(remoteDataSource.getProductsByName("Product"))
                .thenReturn(sampleProductList.right())

        //When
        val result = productsRepository.getProductsByName("Product")

        //Then
        assertEquals(expectedResult, result)
    }

    @Test
    fun `when get details of product by id success, return details from server`() = runBlocking {
        //Given
        val expectedResult: Either<Error, ProductDetails> = Either.Right(sampleProductDetails)
        whenever(remoteDataSource.getProductDetailsById("id"))
                .thenReturn(sampleProductDetails.right())

        //When
        val result = productsRepository.getProductDetailsById("id")

        //Then
        assertEquals(expectedResult, result)
    }

}