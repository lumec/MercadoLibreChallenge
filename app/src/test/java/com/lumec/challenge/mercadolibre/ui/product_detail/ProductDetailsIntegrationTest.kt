package com.lumec.challenge.mercadolibre.ui.product_detail

import androidx.lifecycle.SavedStateHandle
import com.lumec.challenge.mercadolibre.buildDescriptionResponse
import com.lumec.challenge.mercadolibre.buildProductDetailsResponse
import com.lumec.challenge.mercadolibre.buildRepositoryWith
import com.lumec.challenge.mercadolibre.framework.server.Description
import com.lumec.challenge.mercadolibre.framework.server.ProductDetailsResponse
import com.lumec.challenge.mercadolibre.framework.server.ProductListResponse
import com.lumec.challenge.mercadolibre.sampleDetails
import com.lumec.challenge.mercadolibre.ui.*
import com.lumec.challenge.usecases.GetProductDetailsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ProductDetailsIntegrationTest {

    @get:Rule
    val testDispatcher = CoroutinesTestRule()

    @Mock
    lateinit var savedStateHandle: SavedStateHandle

    @Before
    fun setUp() = runTest {
        whenever(savedStateHandle.get<String>("productId")).thenReturn("id")
    }

    @Test
    fun `when search query input event is called, returns list of products from server`() {
        val productId = "M00002"
        val remoteDataSourceDetails = buildProductDetailsResponse(productId)
        val remoteDataSourceDescription = buildDescriptionResponse(productId)
        val viewModel = buildViewModelWith(
            details = remoteDataSourceDetails,
            description = remoteDataSourceDescription
        )

        testDispatcher.advanceUntilIdle()
        val result = viewModel.state.value

        Assert.assertEquals("Product $productId", result.product?.title ?: "")
        Assert.assertEquals(
            "Description of product $productId", result.product?.description ?: ""
        )

    }


    private fun buildViewModelWith(
        products: ProductListResponse = ProductListResponse(emptyList()),
        details: ProductDetailsResponse = sampleDetails,
        description: Description = Description(text = "")
    ): ProductDetailsViewModel {
        val productsRepository = buildRepositoryWith(products, details, description)
        val getProductDetailsUseCase = GetProductDetailsUseCase(productsRepository)
        return ProductDetailsViewModel(getProductDetailsUseCase, savedStateHandle)
    }
}