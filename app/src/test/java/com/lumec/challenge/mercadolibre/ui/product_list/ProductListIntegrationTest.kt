package com.lumec.challenge.mercadolibre.ui.product_list

import com.lumec.challenge.mercadolibre.buildProductListResponse
import com.lumec.challenge.mercadolibre.buildRepositoryWith
import com.lumec.challenge.mercadolibre.framework.server.Description
import com.lumec.challenge.mercadolibre.framework.server.ProductDetailsResponse
import com.lumec.challenge.mercadolibre.framework.server.ProductListResponse
import com.lumec.challenge.mercadolibre.sampleDetails
import com.lumec.challenge.mercadolibre.ui.CoroutinesTestRule
import com.lumec.challenge.usecases.GetProductsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductListIntegrationTest {

    @get:Rule
    val testDispatcher = CoroutinesTestRule()

    @Test
    fun `when search query input event is called, returns list of products from server`() {
        val remoteDataSource = ProductListResponse(buildProductListResponse("product"))
        val viewModel = buildViewModelWith(products = remoteDataSource)
        viewModel.onEvent(ProductListEvents.SearchQueryInput("product"))

        testDispatcher.advanceUntilIdle()
        val result = viewModel.state.value

        Assert.assertEquals("product 3", result.products[2].title)
        Assert.assertEquals("product 5", result.products[4].title)
        Assert.assertEquals("product 10", result.products[9].title)
    }

    @Test
    fun `when erased icon event is called, returns an empty query`() {
        val viewModel = buildViewModelWith()
        viewModel.onEvent(ProductListEvents.EraseIconClicked)

        testDispatcher.advanceUntilIdle()
        val result = viewModel.state.value

        Assert.assertEquals(ProductListUiState(), result)
    }

    private fun buildViewModelWith(
        products: ProductListResponse = ProductListResponse(emptyList()),
        details: ProductDetailsResponse = sampleDetails,
        description: Description = Description(text = "")
    ): ProductListViewModel {
        val productsRepository = buildRepositoryWith(products, details, description)
        val getProductsUseCase = GetProductsUseCase(productsRepository)
        return ProductListViewModel(getProductsUseCase)
    }
}